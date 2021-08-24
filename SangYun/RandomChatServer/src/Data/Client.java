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
    private User user;
    private String ip;
    private int port;
    Socket clientSocket;
    private int connectedClientIdx;
    private boolean matching = false;

    

    PrintWriter writer;
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    public Client(User user){
        this.user = user;
    }

    public Client(Socket socket){
        setSocket(socket);
    }

    public Client(Socket socket, User user){
        this(socket);
        this.user = user;
    }

    public Client(Socket socket, User user, int connectedClientIdx){
        this(socket, user);
        this.connectedClientIdx = connectedClientIdx;
    }
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    // equals 오버라이드 (clients.indexOf 함수 사용 시 호출)
    @Override
    public boolean equals(Object o){
        
        if(this == o)
            return true;
        if(!(o instanceof Client))
            return false;
        Client c = (Client) o;

        return c.getUser() != null && this.user.getId().equals(c.getUser().getId()) ;
    }

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

    public int getConnectedClientIdx() {
        return this.connectedClientIdx;
    }

    public void setConnectedClientIdx(int connectedClientIdx) {
        this.connectedClientIdx = connectedClientIdx;
    }

    public boolean isMatching() {
        return this.matching;
    }

    public void setIsMatching(boolean isMatching) {
        this.matching = isMatching;
    }
}
