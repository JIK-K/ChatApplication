/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Client;
import Util.Debug;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ssy02
 */
public class SocketManager {
    ArrayList<Client> clients;
    ServerSocket serverSocket;
    int port;
    
    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket socket;
        
        public ClientHandler(Socket clientSocket){
            try{
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
                        
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("" + message);
                    
                    tellConnectedClient(message);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public SocketManager(int port){
        try {
            this.port = port;
            
            serverSocket = new ServerSocket(port);
            go();
            
            
        } catch (IOException ex) {
            Debug.log("Socket construct exception");
        }
    }
    public void go(){
        clients = new ArrayList<Client>();
        System.out.println("server 켜져따");
        try{
            while(true){
                Socket socket = serverSocket.accept();
                Client connectedClient = new Client();
                
                connectedClient.setSocket(socket);
                connectedClient.println("랜챗에 오신걸 환영합니다.");
                
                Thread t = new Thread(new ClientHandler(connectedClient.getClientSocket()));
                t.start();

                Debug.log("client ip : " + connectedClient.getClientSocket().getInetAddress());
            }   
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void tellConnectedClient(String Message){
        client.
    }
    public void tellEveryone(String message){
        for(Client client : clients){
            try{
                PrintWriter writer = client.getWriter();
                writer.println(message);
                writer.flush();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
