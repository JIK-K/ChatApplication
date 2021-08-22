/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author JIK
 */
public class SocketManager {
    ArrayList<Client> clients;
    ServerSocket serverSocket;
    int port;
    
    public SocketManager(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            go();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
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
                    tellEveryone(message);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
    
    public void go(){
        clients = new ArrayList<Client>();
        System.out.println("Server on");
        try{
            while(true){
                Socket socket = serverSocket.accept();
                Client connetedClient = new Client();
                
                connetedClient.setSocket(socket);
                connetedClient.println("누군가가 접속 했습니다");
                connetedClient.getClientIp();
                
                clients.add(connetedClient);
                
                Thread t = new Thread(new ClientHandler(connetedClient.getClientSocket()));
                t.start();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
