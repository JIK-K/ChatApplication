/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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
    public ArrayList<User> user;
    PrintWriter writer;
    String ip;
    int port;
    Socket clientSocket;
    
    void getUserInformation(){
        //user의 정보를 입력받는 메소드
    }
    
    public void checkUserGender(){
        //User 성별 체크 하는 메소드
    }
    
    public PrintWriter getWriter(){
        
        return writer;
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
    
    public void getClientPort () {
        //접속자 port번호
        //.getPort? .getLocalPort?
        
    }
    
    public void sendMessageToClient(){
        //클라이언트에게 메세지를 보낼 PrintWriter
        
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
    
    public void println(String message){
                
        System.out.println("" + message); //이게 서버 창에 뜬다
        writer.println(message); //이게 사용자 창에 뜨고
    }
    
    
    public Socket getClientSocket(){
        
        return clientSocket;
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

