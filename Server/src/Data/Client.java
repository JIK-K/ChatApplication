/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Util.Debug;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JIK
 */
public class Client {
    private User user;
    private String ip;
    private int port;
    PrintWriter writer;
    Socket clientSocket;
    public int connectedClientIdx = -1;
    public int status = 0;
    
    // ------------------------------------------------------------------------//
    //
    // ------------------------------------------------------------------------//
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
    // ------------------------------------------------------------------------//
    //
    // ------------------------------------------------------------------------//
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Client))
            return false;
        Client c = (Client) o;
        
        return c.getUser() != null && this.user.getId().equals(c.getUser().getId());
                    
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
    
    void getUserInformation(){
        //user의 정보를 입력받는 메소드
    }
    
    public void checkUserGender(){
        //User 성별 체크 하는 메소드
    }
    
    public void getClientIp(){
        //접속자 IP 얻기
        InetSocketAddress socketaddr = (InetSocketAddress)clientSocket.getRemoteSocketAddress();
        InetAddress inaddr = socketaddr.getAddress();
        
        SocketAddress socketAddress = clientSocket.getRemoteSocketAddress();
        if(socketAddress instanceof InetSocketAddress){
            InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
            if(inetAddress instanceof Inet4Address)
                System.out.println("IPv4" + inetAddress);
            else if(inetAddress instanceof Inet6Address)
                System.out.println("TPv6" + inetAddress);
            else
                System.err.println("Not an IP address");
        }else{
            System.err.println("Not an internet protocol socket");
        }
    }

    
    
    
    
    
    //==================================================================//
    //==================================================================//
    public void println(String message){
        Debug.log(message);
        System.out.println("" + message); //이게 서버 창에 뜬다
        writer.println(message); //이게 사용자 창에 뜨고
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

    public int isMatching() {
        return this.status;
    }

    public void setIsMatching(int status) {
        this.status = isMatching();
    }

    public void chatting(ArrayList<Client> matchingUser, String message) {
        matchingUser.get(connectedClientIdx).println(message);
    }
    
    
    //==================================================================//
    //==================================================================//
    
    
    public String getString(){
        //문자열 입력받는 메소드
        String input = "";
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextLine();
                break;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return input;
    }
    
    public int getInteger(){
        int input = 0;
        
        return input;
    }
    
    public boolean getBoolean(){
        boolean input = false;
        
        
        return input;
    }
    
}

