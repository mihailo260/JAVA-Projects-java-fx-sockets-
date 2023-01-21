package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bioRithm extends Application {
    Matcher matcher;

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        Label lbl = new Label("Enter date of birth");
        TextField tf = new TextField();
        tf.setStyle("-fx-width:100% ; -fx-padding:5px; -fx-font-weight:bold; ");
        Pattern pattern = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
        tf.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                matcher = pattern.matcher(tf.getText());
                if (matcher.matches()) {
                    tf.setStyle("-fx-width:100% ; -fx-padding:5px; -fx-font-weight:bold; -fx-border-color:green");
                } else {
                    tf.setStyle("-fx-width:100% ; -fx-padding:5px; -fx-font-weight:bold; -fx-border-color:red");
                }
            }
        });
        Button btn = new Button("calculate bio rithm");
        btn.setOnAction(e -> {
            if (tf.getText().length() == 0 || !matcher.matches()) {
                return;
            }
            LocalDate begining = LocalDate.parse(tf.getText());
            LocalDate now = LocalDate.now();
            try {
                Socket s = new Socket("localhost", 1234);
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                writer.println(ChronoUnit.DAYS.between(begining, now));
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Label lbl3 = new Label(reader.readLine());
                vbox.getChildren().add(lbl3);
                reader.close();
                writer.close();
                s.close();
            } catch (Exception es) {
                es.printStackTrace();
            }

        });
        Label lbl2 = new Label("format yyyy-mm-dd");
        lbl2.setStyle("-fx-font-weight:bolder;");
        vbox.getChildren().addAll(lbl, tf, btn, lbl2);
        Scene sc = new Scene(vbox, 300, 300);
        stage.setScene(sc);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
