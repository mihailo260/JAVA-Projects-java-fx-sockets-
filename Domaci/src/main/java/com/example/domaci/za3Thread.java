package com.example.domaci;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class za3Thread extends Thread{
    Label label;
    za3Thread(Label label){
        this.label = label;
    }
    public void run(){

        try {
            int sec=0;
            label.setText(Integer.toString(sec));
            while (sec <10){
                sec++;
                label.setText(Integer.toString(sec));

                System.out.println(sec);
                sleep(1000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
