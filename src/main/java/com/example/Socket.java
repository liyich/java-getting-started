package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class Socket {
	public static void main(String args[]) throws IOException {
      try {  
          ServerSocket serverScoket = new ServerSocket(8080);  
          java.net.Socket socket = null;  
          int num=0;  
          while (true) {  
              socket=serverScoket.accept();  
              SocketThread serverThread=new SocketThread(socket);  
              serverThread.start();  
              num++;  
          }  

      } catch (IOException e) {  
          e.printStackTrace();  
      }  
  }  
}