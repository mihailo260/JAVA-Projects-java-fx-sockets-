package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class zad3 extends Application {
    int z = 0;
    VBox vbox;
    HBox hbox;
    int Timer = 0;
    int numErrors = 0;
    Scene sc;
    Button btn2;
    ArrayList<Integer> list = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<Number> reslist = new ArrayList<>();
        vbox = new VBox();
        btn2 = new Button("START");
        vbox.getChildren().add(btn2);
        btn2.setStyle("-fx-background-color: #4CAF50; -fx-color: white; -fx-padding: 15px 32px; -fx-font-size: 16px;");
        btn2.setOnAction(e -> {
            btn2.setDisable(true);
            int z = 0;
            for (int i = 0; i < 3; i++) {
                hbox = new HBox();
                for (int j = 0; j < 3; j++) {
                    Button btn = new Button(Integer.toString(z++));
                    btn.setMinSize(100, 100);
                    btn.setStyle("-fx-background-color: Yellow; -fx-border-color: green;");
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (btn.getText().equals(Integer.toString(list.get(0)))) {
                                list.remove(0);
                                btn.setStyle("-fx-background-color:green");
                                btn.setDisable(true);
                            } else {
                                btn.setStyle("-fx-background-color:red");
                                numErrors++;
                                if (numErrors == 3) {
                                    System.out.println("you Lost");
                                    Stage dialog = new Stage();
                                    Button btn = new Button("YOU LOST");
                                    btn.setStyle("-fx-background-color:red");
                                    btn.setOnAction(e -> {
                                        dialog.close();
                                    });
                                    Scene sc = new Scene(btn, 100, 100);
                                    dialog.setScene(sc);
                                    dialog.initOwner(stage);
                                    dialog.initModality(Modality.APPLICATION_MODAL);
                                    dialog.showAndWait();
                                }

                                return;
                            }
                            if (list.size() == 0) {
                                System.out.println("you Won");
                                Stage dialog = new Stage();
                                Button btn = new Button("YOU WON");
                                btn.setOnAction(e -> {
                                    dialog.close();
                                });
                                Scene sc = new Scene(btn, 100, 100);
                                dialog.setScene(sc);
                                dialog.initOwner(stage);
                                dialog.initModality(Modality.APPLICATION_MODAL);
                                dialog.showAndWait();
                            }

                        }
                    });

                    hbox.getChildren().add(btn);
                }
                vbox.getChildren().add(hbox);
            }
            runFunction();
        });

        btn2.setMinSize(40, 40);
        sc = new Scene(vbox, 400, 400);
        stage.setScene(sc);
        stage.show();


    }

    public void runFunction() {

        Thread thread = new Thread(new Runnable() {

            @Override

            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                while (Timer < 9) {
                    try {
                        int random_int = (int) Math.floor(Math.random() * (8 - 0 + 1) + 0);
                        if (haselement(random_int)) {
                            for (Node nodes : vbox.getChildren()) {
                                if (nodes instanceof HBox) {
                                    for (Node elem : ((HBox) nodes).getChildren()) {
                                        if (elem instanceof Button) {
                                            if (((Button) elem).getText().equals(Integer.toString(list.get(list.size() - 1)))) {
                                                elem.setStyle("-fx-background-color:green");
                                                System.out.print(list.get(list.size() - 1));
                                                sleep(1000);
                                                elem.setStyle("-fx-background-color:Yellow; -fx-border-color: green");
                                                Timer++;
                                            }

                                        }

                                    }
                                }

                            }
                        }


                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(updater);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public boolean haselement(int random_int) {
        for (int elem : list) {
            if (elem == random_int) {
                return false;
            }
        }
        list.add(random_int);
        return true;
    }

    public static void main(String[] args) {
        launch();
    }
}


