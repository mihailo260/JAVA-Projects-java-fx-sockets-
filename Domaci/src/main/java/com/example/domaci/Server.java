package com.example.domaci;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
        int MAT[][] = new int[3][3];


    Server() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            while (true) {
                Socket s = ss.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                String readerRes = reader.readLine();
                createMatrix(readerRes);
                String writerRes = "";
                for (int i = 0; i < MAT[0].length; i++) {
                    for (int j = 0; j < MAT[i].length; j++) {
                        writerRes += " "+MAT[i][j];
                    }
                }
                System.out.println(writerRes);
                writer.println(writerRes);
                reader.close();
                writer.close();
                s.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    int[][] createMatrix(String readerRes) {
        int x = Integer.parseInt(readerRes);
        MAT[0][0] = x+1;
        MAT[0][2] = x+3;
        MAT[1][1] = x;
        MAT[2][0] = x-3;
        MAT[2][2] = x-1;
        int magicConstant = 0;
        for (int i = 0; i < 3; i++) {
            magicConstant += MAT[i][i];
        }
        MAT[0][1] = magicConstant- (MAT[0][0] + MAT[0][2]);
        MAT[1][0] = magicConstant- (MAT[0][0] + MAT[2][0]);
        MAT[1][2] = magicConstant- (MAT[0][2] + MAT[2][2]);
        MAT[2][1] = magicConstant- (MAT[2][0] + MAT[2][2]);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + (int)MAT[i][j] + " ");
            }
            System.out.println();
        }
        return MAT;
    }

}
