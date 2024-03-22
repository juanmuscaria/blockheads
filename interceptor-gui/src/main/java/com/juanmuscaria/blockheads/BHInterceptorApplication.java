package com.juanmuscaria.blockheads;

import atlantafx.base.theme.CupertinoDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class BHInterceptorApplication extends Application {
  private static final Logger logger = LoggerFactory.getLogger(BHInterceptorApplication.class);

  public static void main(String... args) {
    logger.info("Launching JavaFX application");
    BHInterceptorApplication.launch(BHInterceptorApplication.class, args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
    stage.setMinWidth(600);
    stage.setMinHeight(500);
    stage.setWidth(800);
    stage.setHeight(600);
    stage.setScene(new Scene(FXMLLoader.load(
      Objects.requireNonNull(getClass().getResource("/ui/Interceptor.fxml"),
        "Unable to load JavaFX resources"))));
    stage.setTitle("Interceptor");
    stage.show();
  }
}
