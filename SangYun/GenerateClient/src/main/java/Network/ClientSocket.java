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

import Data.User;
import Util.Debug;

/**
 *
 * @author ssy02
 */
public class ClientSocket {
    //------------------------------------------------------------------------//
    //
<<<<<<< HEAD
    public static int numOfClient;
    public String clientName;
    
    BufferedReader reader;
    public PrintWriter writer;
=======
    public static int numOfClient; 
    
    BufferedReader reader;
    private PrintWriter writer;
    private User user;


>>>>>>> cf6ba475a1bc3891af83dd59df43d4dd9b809244
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
<<<<<<< HEAD
            socket = new Socket("127.0.0.1", 5000);
            clientName = "client" + numOfClient++;
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true );
            System.out.println(clientName + " on");
            // "###id/nickname/random/gender/###"
            writer.println("###client" + numOfClient + "/nickname/계대/19/gender/###");
=======
            socket = new Socket("127.0.0.1", 4242);
            user = new User("client" + numOfClient++);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true );

            Debug.log(user.getId() + " on");
            
            writer.println("###" + user + "random" + "###");
>>>>>>> cf6ba475a1bc3891af83dd59df43d4dd9b809244
            writer.flush();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void go(){
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }
    public void send(String message){
        writer.println(clientName + ":" + message);
        writer.flush();
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
    //------------------------------------------------------------------------//
    // getter and setter
    //------------------------------------------------------------------------//
    public PrintWriter getWriter() {
        return this.writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
