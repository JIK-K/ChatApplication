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
    public static int numOfClient; 
    
    BufferedReader reader;
    private PrintWriter writer;
    private User user;


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
            user = new User("client" + numOfClient++);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true );

            Debug.log(user.getId() + " on");
            
            writer.println("###" + user + "random" + "###");
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
                    System.out.println("??????????????? ??? ????????? : " + message);
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
