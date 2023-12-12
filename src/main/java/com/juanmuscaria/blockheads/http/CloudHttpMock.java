package com.juanmuscaria.blockheads.http;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.nio.charset.StandardCharsets;

public class CloudHttpMock {
  public static final String CLOUD_SERVER = "173.230.144.247";
  public static final String PRICE_SERVER = "priceserver.theblockheads.net";
  public static String IP = "";
  public static int PORT = 0;

  public static void startProxyServer() {
    HttpProxyServerConfig config = new HttpProxyServerConfig();
    Gson gson = new Gson();
    config.setHandleSsl(true);
    new HttpProxyServer()
      .serverConfig(config)
      .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
        @Override
        public void init(HttpProxyInterceptPipeline pipeline) {
          pipeline.addLast(new FullResponseIntercept() {

            @Override
            public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
              String host = httpRequest.headers().get(HttpHeaderNames.HOST);
              return host != null && host.contains(CLOUD_SERVER);
            }

            @Override
            public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
              System.out.println(httpResponse.toString());
              try {
                JsonElement element = JsonParser.parseString(httpResponse.content().toString(StandardCharsets.UTF_8));
                if (element.isJsonObject()) {
                  JsonObject body = (JsonObject) element;
                  if (body.get("ip") != null) {
                    IP = body.get("ip").getAsString();
                    PORT = body.get("port").getAsInt();
                    System.out.printf("Redirecting %s:%s to proxy", IP, PORT);
                    body.remove("ip");
                    body.remove("port");
                    body.addProperty("ip", "10.0.2.2");
                    body.addProperty("port", 15151);
                    httpResponse.content().clear();
                    httpResponse.content().writeBytes(gson.toJson(body).getBytes(StandardCharsets.UTF_8));
                  }
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
        }
      })
      .start(8080);
  }
}
