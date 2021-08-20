/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Util.Debug;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ssy02
 */
public class Client {
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    User user;
    String ip;
    int port;
    Socket clientSocket;
    PrintWriter writer;
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    public void setSocket(Socket socket){
        try{
            clientSocket = socket;

            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true );
            ip = socket.getInetAddress().toString();
            port = socket.getLocalPort();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------//
    // getter and setter
    //------------------------------------------------------------------------//
    public void println(String message){
        Debug.log(message);
        writer.println(message);
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
