/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Client;
import Data.User;
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
    private static SocketManager instance;
    public ArrayList<Client> clients;
    private ServerSocket serverSocket;
    int port;
    //--------------------------------------------------------------------------------------------//
    // ClientHandler (내부 클래스)
    //--------------------------------------------------------------------------------------------//
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
        //----------------------------------------------------------------------------------------//
        // 클라이언트 소켓이 read 하는 부분
        @Override
        public void run() {
            String message;
            try{
                while((message = reader.readLine()) != null){
<<<<<<< HEAD
                    System.out.println("" + message);
                    
                    tellConnectedClient(message);
=======
                    if(message.startsWith("###")){
                        message = message.replace("###","");
                        String[] info = message.split("/");
                        String matchingCondition = info[6];
                        
                        User user = parseUser(info);
                        clients.get(clients.size() - 1).setUser(user);
                        Debug.log("user : " + clients.get(clients.size() - 1).getUser());
                        
                    }
                    else{
                        String[] splitMessage = message.split(":");
                        String clientName = splitMessage[0];
                        String msg = splitMessage[1];

                        Client client = new Client(new User(clientName));
                        int fromIdx = clients.indexOf(client);
                        
                    }
>>>>>>> cf6ba475a1bc3891af83dd59df43d4dd9b809244
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //--------------------------------------------------------------------------------------------//
    // constructor
    //--------------------------------------------------------------------------------------------//
    public SocketManager(){
        try {
            this.port = 4242;
            
            serverSocket = new ServerSocket(port);
            go();
            
            
        } catch (IOException ex) {
            Debug.log("Socket construct exception");
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
    //--------------------------------------------------------------------------------------------//
    // singleton pattern
    //--------------------------------------------------------------------------------------------//
    private static class SingleTonHolder{
        private static final SocketManager instance = new SocketManager();
    }
    
    
    public static SocketManager getInstance(){
        return SingleTonHolder.instance;
    }
    //--------------------------------------------------------------------------------------------//
    // 메서드
    //--------------------------------------------------------------------------------------------//
    public void go(){
        clients = new ArrayList<Client>();
        System.out.println("server 켜져따");
        try{
            while(true){
                Socket socket = serverSocket.accept();
                Client connectedClient = new Client(socket);
                clients.add(connectedClient);
                
                connectedClient.println("랜챗에 오신걸 환영합니다.");
                
                Thread.sleep(50);
                
                Thread t = new Thread(new ClientHandler(connectedClient.getClientSocket()));
                t.start();
                

                Debug.log("client ip : " + connectedClient.getClientSocket().getInetAddress());
            }   
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
<<<<<<< HEAD
    public void tellConnectedClient(String Message){
        client.
    }
=======

    public User parseUser(String[] info){
        User user = new User(info[0], info[1], info[2], info[3], info[4], Integer.parseInt(info[5]));

        return user;
    }

>>>>>>> cf6ba475a1bc3891af83dd59df43d4dd9b809244
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
