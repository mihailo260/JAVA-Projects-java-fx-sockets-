package com.example.domaci;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.*;
import java.net.Socket;

public class HelloApplication extends Application {


    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        HBox pane = new HBox(15);

        Label lbl = new Label("add number between 5-30");
        TextField tf = new TextField();
        Button btn = new Button("magic matrix 3X3");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if (Integer.parseInt(tf.getText()) < 5 || Integer.parseInt(tf.getText()) > 30) {
                        System.out.println("ERROR");
                        return;
                    }
                    try {

                        Socket s = new Socket("localhost", 1234);
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                        writer.println(tf.getText());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        String readerRes = (reader.readLine());
                        String[] arrOfStr = readerRes.split(" ");
                        FlowPane flex = new FlowPane();
                        for (int i = 1; i < arrOfStr.length; i++) {
                            Button btn = new Button(arrOfStr[i]);
                            btn.setMinWidth(100);
                            flex.getChildren().add(btn);
                        }
                        pane.getChildren().add(flex);


                        reader.close();
                        writer.close();
                        s.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (NumberFormatException e) {
                    return;
                }


            }
        });
        pane.getChildren().addAll(lbl, tf, btn);
        Scene scene = new Scene(pane,600,500);
        stage.setScene(scene);
        stage.show();



    }
}

//awadwwdadw