package com.jtfu.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8000);
       // while (true){
            Socket socket=serverSocket.accept();
            new Thread(()->{
                InputStream is = null;
                try {
                     is=socket.getInputStream();
                     byte[] bytes=new byte[1024];
                     int index;
                     while (true){
                         while ((index=is.read(bytes))!=-1){
                             System.out.println(new String(bytes,0,index));
                         }
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        //}
    }
}
