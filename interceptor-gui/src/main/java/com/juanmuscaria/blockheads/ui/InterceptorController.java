package com.juanmuscaria.blockheads.ui;

import atlantafx.base.theme.Styles;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Context;
import com.juanmuscaria.blockheads.BHInterceptor;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.InterceptedPacket;
import com.juanmuscaria.blockheads.ui.component.HexEditor;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import pk.ansi4j.core.DefaultFunctionFinder;
import pk.ansi4j.core.DefaultParserFactory;
import pk.ansi4j.core.DefaultTextHandler;
import pk.ansi4j.core.api.*;
import pk.ansi4j.core.api.iso6429.ControlSequenceFunction;
import pk.ansi4j.core.iso6429.*;
import pk.ansi4j.css.DefaultAttributeContext;
import pk.ansi4j.css.DefaultCssFunctionProcessor;
import pk.ansi4j.css.api.AttributeContext;
import pk.ansi4j.css.api.CssFunctionProcessor;
import pk.ansi4j.css.api.color.Color8;
import pk.ansi4j.css.api.color.PaletteType;
import pk.ansi4j.css.color.VisualStudioCodePalette16;
import pk.ansi4j.css.text.DefaultTextAttributeConfig;
import pk.ansi4j.css.text.DefaultTextAttributeResolver;
import pk.ansi4j.css.text.JavaFxCssGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class InterceptorController {
  private static final Logger logger = LoggerFactory.getLogger(InterceptorController.class);
  private final ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
    var thread = new Thread(runnable, "PROXY");
    thread.setDaemon(true);
    return thread;
  });
  private final AtomicReference<Future<?>> interceptorTask = new AtomicReference<>();
  private final AtomicReference<IntObjectMap<InterceptedPacket>> interceptedPackets = new AtomicReference<>(new IntObjectHashMap<>());
  public AnchorPane root;
  public ListView<EntryItem> packetList;
  public TextField packetIdField;
  public TextField compressedField;
  public TextField enetChannelField;
  public TextField flagsField;
  public TextField mappingField;
  public TextField sideField;
  public TextField serverHostname;
  public TextField serverPort;
  public TextField proxyHostname;
  public TextField proxyPort;
  public Button proxyStartStop;
  public Button stateButton;
  public BorderPane hexTab;
  private InterceptedPacket selectedPacket;
  private volatile Throwable proxyException;

  private static String getCauseText(Throwable e) {
    var dejavu = Collections.<Throwable>newSetFromMap(new IdentityHashMap<>());
    var current = e;
    while (current.getCause() != null) {
      if (dejavu.add(current)) {
        current = current.getCause();
      } else {
        break;
      }
    }
    return current.getMessage();
  }

  private static ListCell<EntryItem> packetListCell(ListView<EntryItem> __) {
    return new ListCell<>() {
      @Override
      protected void updateItem(EntryItem item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (item != null) {
          item.highlightedText.setAlignment(Pos.CENTER_LEFT);
          setGraphic(item.highlightedText);
          setDisable(item.data == null);
        }
      }
    };
  }

  @FXML
  void initialize() {
    // Dirty hack to get log messages
    var rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    var appender = new LogbackListAppender(rootLogger.getLoggerContext());
    appender.start();
    rootLogger.addAppender(appender);

    var items = packetList.getItems();
    items.addListener((ListChangeListener<EntryItem>) change -> {
      while (packetList.getSelectionModel().getSelectedItem() == null && change.next()) {
        if (change.wasAdded()) {
          Platform.runLater(() -> packetList.scrollTo(items.size() - 1));
          break;
        }
      }
    });

    packetList.setCellFactory(InterceptorController::packetListCell);

    packetList.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
      if (newValue == null) {
        selectedPacket = null;
      } else {
        selectedPacket = newValue.data;
      }
      updateUI();
    });

    Platform.runLater(() -> root.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (_) -> {
      executor.shutdownNow();
    }));
  }

  private void updateUI() {
    Platform.runLater(() -> {
      if (selectedPacket == null) {
        packetIdField.setText(null);
        compressedField.setText(null);
        enetChannelField.setText(null);
        flagsField.setText(null);
        mappingField.setText(null);
        sideField.setText(null);
        hexTab.setCenter(null);
      } else {
        packetIdField.setText(String.valueOf(selectedPacket.getId()));
        compressedField.setText(String.valueOf(BHHelper.isProbablyCompressed(selectedPacket.getRawData())));
        enetChannelField.setText(String.valueOf(selectedPacket.getChannel()));
        flagsField.setText(Integer.toBinaryString(selectedPacket.getFlags()));
        mappingField.setText(selectedPacket.getMapping() == null ? null : String.valueOf(selectedPacket.getMapping()));
        sideField.setText(selectedPacket.getDirection().name());
        hexTab.setCenter(new HexEditor(selectedPacket.getRawData()));
      }
    });
  }

  public void onProxyStartStop(ActionEvent event) {
    event.consume();
    var interceptor = interceptorTask.get();
    if (interceptor != null) {
      interceptor.cancel(true);
    } else {
      startInterceptor();
    }
  }

  public void onStateButton(ActionEvent event) {
    if (proxyException != null) {
      event.consume();
      var alert = GuiHelper.createExceptionDialog("Proxy Exception", "An exception occurred while running the proxy",
        getCauseText(proxyException), proxyException, root.getScene());
      alert.show();
    }
  }

  private void startInterceptor() {
    proxyException = null;
    setParamEditing(false);
    setStateButton("Running", true, Styles.SUCCESS, Styles.FLAT);
    proxyStartStop.setText("Stop Proxy");
    proxyStartStop.getStyleClass().add(Styles.DANGER);
    interceptorTask.set(executor.submit(() -> {
      var interceptor = new BHInterceptor();
      interceptedPackets.set(interceptor.getPackets());
      var cmd = new CommandLine(interceptor);
      try {
        var result = cmd.parseArgs(genArgs().toArray(new String[0]));
        var returnCode = cmd.getExecutionStrategy().execute(result);
        if (returnCode == 0) {
          Platform.runLater(() -> {
            setStateButton("Not Started", true, Styles.FLAT);
          });
        } else {
          logger.warn("Interceptor exited with code {}, this should not happen", returnCode);
          Platform.runLater(() -> setStateButton("Not Started", true, Styles.FLAT));
        }
      } catch (Throwable e) {
        interceptException(e);
      }
      Platform.runLater(() -> {
        setParamEditing(true);
        proxyStartStop.setText("Start Proxy");
        proxyStartStop.getStyleClass().remove(Styles.DANGER);
      });
      interceptorTask.set(null);
    }));
  }

  private void setParamEditing(boolean editable) {
    serverHostname.setEditable(editable);
    serverPort.setEditable(editable);
    proxyHostname.setEditable(editable);
    proxyPort.setEditable(editable);
  }

  private ArrayList<String> genArgs() {
    var args = new ArrayList<String>();
    args.add("-D");
    if (!serverHostname.getText().isBlank()) {
      args.add("-SH");
      args.add(serverHostname.getText());
    }
    if (!serverPort.getText().isBlank()) {
      args.add("-S");
      args.add(serverPort.getText());
    }
    if (!proxyHostname.getText().isBlank()) {
      args.add("-PH");
      args.add(proxyHostname.getText());
    }
    if (!proxyPort.getText().isBlank()) {
      args.add("-P");
      args.add(proxyPort.getText());
    }
    return args;
  }

  private void interceptException(Throwable e) {
    Platform.runLater(() -> {
      proxyException = e;
      setStateButton("Error occurred, click to see details", false, Styles.DANGER);
    });
  }

  private void setStateButton(String text, boolean disabled, String... styles) {
    stateButton.setText(text);
    var styleClass = stateButton.getStyleClass();
    styleClass.removeAll(Styles.FLAT, Styles.SUCCESS, Styles.DANGER);
    styleClass.addAll(styles);
    stateButton.setDisable(disabled);
  }

  @AllArgsConstructor
  public static class EntryItem {
    private HBox highlightedText;
    private InterceptedPacket data;
  }

  private class LogbackListAppender extends AppenderBase<ILoggingEvent> {
    private final PatternLayout layout = new PatternLayout();
    private final ParserFactory factory = new DefaultParserFactory.Builder()
      .environment(Environment._7_BIT)
      .textHandler(new DefaultTextHandler())
      .functionFinder(new DefaultFunctionFinder())
      .functionHandlers(
        new C0ControlFunctionHandler(),
        new C1ControlFunctionHandler(),
        new ControlSequenceHandler(),
        new IndependentControlFunctionHandler(),
        new ControlStringHandler())
      .build();
    private final AttributeContext context = new DefaultAttributeContext(List.of(new DefaultTextAttributeConfig.Builder()
      .defaultForegroundColor(Color8.WHITE, PaletteType.PALETTE_16)
      .defaultBackgroundColor(Color8.BLACK, PaletteType.PALETTE_16)
      .fontFamilies(List.of(""))
      .extraColorsEnabled(true)
      .palette16(new VisualStudioCodePalette16())
      .build()));
    private final CssFunctionProcessor processor = new DefaultCssFunctionProcessor.Builder()
      .resolvers(new DefaultTextAttributeResolver())
      .generators(new JavaFxCssGenerator())
      .build();

    LogbackListAppender(Context ctx) {
      layout.setContext(ctx);
      layout.setPattern("%red([%thread]) %highlight(%-5level) %magenta(%logger{36}) - %msg%n");
      layout.start();
      this.setContext(ctx);
    }

    @Override
    protected void append(ILoggingEvent event) {
      String logMessage = layout.doLayout(event);
      var sessionId = event.getMDCPropertyMap().get("packetSession");
      InterceptedPacket packet;
      if (sessionId != null) {
        packet = interceptedPackets.get().remove(Integer.valueOf(sessionId));
        if (packet == null) {
          logger.warn("Intercepted packet {} was null", sessionId);
        }
      } else {
        packet = null;
      }

      Platform.runLater(() -> packetList.getItems().add(new EntryItem(highlightText(logMessage), packet)));
    }

    private HBox highlightText(String ansiText) {
      var parser = factory.createParser(ansiText);
      var box = new HBox();
      var style = "";
      var text = new StringBuilder();
      Fragment fragment;

      while ((fragment = parser.parse()) != null) {
        if (fragment.getType() == FragmentType.TEXT) {
          // Part of a text, append it
          var textFragment = (TextFragment) fragment;
          text.append(textFragment.getText());
        } else if (fragment.getType() == FragmentType.FUNCTION) {
          var functionFragment = (FunctionFragment) fragment;
          if (functionFragment.getFunction() == ControlSequenceFunction.SGR_SELECT_GRAPHIC_RENDITION) {
            // We found an ansi code, start a new style
            var styledText = new Text(text.toString());
            styledText.setStyle(style);
            box.getChildren().add(styledText);
            text.setLength(0);
            List<String> declarations = processor.process(functionFragment, context);
            style = String.join(";", declarations);
          }
        }
      }

      // Append any leftover text with the last style we had
      if (!text.isEmpty()) {
        var styledText = new Text(text.toString());
        styledText.setStyle(style);
        box.getChildren().add(styledText);
      }

      return box;
    }
  }
}
