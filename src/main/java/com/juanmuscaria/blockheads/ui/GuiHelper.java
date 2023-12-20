package com.juanmuscaria.blockheads.ui;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import net.datafaker.Faker;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GuiHelper {
  public static final Faker FAKER = new Faker();

  public static Alert createExceptionDialog(String title, String header, String content, Throwable e, Scene owner) {
    var alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    var stringWriter = new StringWriter();
    var printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);

    var textArea = new TextArea(stringWriter.toString());
    textArea.setEditable(false);
    textArea.setWrapText(false);
    textArea.setMaxWidth(Double.MAX_VALUE);
    textArea.setMaxHeight(Double.MAX_VALUE);
    GridPane.setVgrow(textArea, Priority.ALWAYS);
    GridPane.setHgrow(textArea, Priority.ALWAYS);

    var contentPane = new GridPane();
    contentPane.setMaxWidth(Double.MAX_VALUE);
    contentPane.add(new Label("Full stacktrace:"), 0, 0);
    contentPane.add(textArea, 0, 1);

    alert.getDialogPane().setExpandableContent(contentPane);
    if (owner != null) {
      alert.initOwner(owner.getWindow());
    }
    return alert;
  }

}
