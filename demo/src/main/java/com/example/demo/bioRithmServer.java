package com.example.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class bioRithmServer {

    bioRithmServer() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            while (true) {
                Socket s = ss.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                int t = Integer.parseInt(reader.readLine());
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                double r1 = Math.sin((2*Math.PI*t)/23)*100;
                double r2 = Math.sin((2*Math.PI*t)/28)*100;
                double r3 = Math.sin((2*Math.PI*t)/33)*100;
                String res = "Physical:"+Double.toString(r1)+" Emotional:"+Double.toString(r2)+" intelectual:"+Double.toString(r3);
                writer.println(res);
                writer.close();
                reader.close();
                s.close();
            }
        } catch (Exception e) {
            return;
        }


    }

    public static void main(String[] args) {
        new bioRithmServer();
    }
}
