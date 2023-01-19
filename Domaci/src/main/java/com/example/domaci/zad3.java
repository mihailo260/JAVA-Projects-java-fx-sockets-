package com.example.domaci;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class zad3 extends Application {
    int cntr = 0;
    ArrayList<Integer> arrTemp = new ArrayList<>();
    Button btn;
    public static void main(String[] args) {
        launch();
    }
    public void start(Stage stage) {

        FlowPane pane = new FlowPane();
        pane.setMaxWidth(500);



        for (int j = 0; j < 16; j++) {
            int randomElement = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if (listContains(arrTemp, randomElement)) {
                arrTemp.add(randomElement);
                Button btn = new Button(Integer.toString(randomElement));
                btn.setMinWidth(100);


                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (Integer.parseInt(btn.getText()) == Collections.max(arrTemp) && cntr == 0) {
                            cntr = 1;
                            removeEL(Integer.parseInt(btn.getText()));
                            btn.setText(".");
                            btn.setDisable(true);
                        } else if (Integer.parseInt(btn.getText()) == Collections.min(arrTemp) && cntr == 1) {
                            cntr = 0;
                            removeEL(Integer.parseInt(btn.getText()));
                            btn.setText(".");
                            btn.setDisable(true);
                        }
                        if (arrTemp.size() == 0) {
                            Stage dialog = new Stage();
                            dialog.setMaxWidth(50);
                            dialog.initOwner(stage);
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            HBox box = new HBox();
                            Label win = new Label("YOU WOON!");
                            win.setAlignment(Pos.CENTER);
                            box.getChildren().add(win);
                            Scene sc = new Scene(box,100,100);
                            dialog.setScene(sc);
                            dialog.showAndWait();
                        }
                    }
                });
                pane.getChildren().add(btn);

            } else {
                j--;
            }

        }
        HBox box = new HBox();
        Button button = new Button("START");
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                box.getChildren().addAll(pane);
            }
        });
        box.getChildren().addAll(button);
        Scene scene = new Scene(box,500,400);
        stage.setScene(scene);
        stage.show();
    }

    boolean listContains(ArrayList<Integer> arrTemp, int randomElement) {
        for (int elem1 : arrTemp) {
            if (elem1 == randomElement) {
                return false;
            }
        }
        return true;
    }

    void removeEL(int elem) {
        arrTemp.remove(Integer.valueOf(elem));
    }


}
