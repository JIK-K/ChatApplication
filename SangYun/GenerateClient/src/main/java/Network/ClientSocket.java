/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ssy02
 */
public class ClientSocket {
    //------------------------------------------------------------------------//
    //
    public static int numOfClient;
    String clientName;
    
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    public ClientSocket(){
        setUpNetworking();
        go();
    }
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    private void setUpNetworking(){
        try {
            socket = new Socket("127.0.0.1", 4242);
            clientName = "client " + numOfClient++;
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true );
            System.out.println(clientName + " on");
            
            writer.println("접속 : " + clientName);
            writer.flush();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void go(){
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }
    
    public class IncomingReader implements Runnable {
        public void run(){
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("서버로부터 온 메시지 : " + message);
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
