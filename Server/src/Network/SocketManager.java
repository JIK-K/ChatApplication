/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Client;
import Data.User;
import Util.Debug;
import server.ServerGUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.SwingWorker;

/**
 *
 * @author JIK
 */
public class SocketManager implements Runnable{
    private static SocketManager instance;
    public ArrayList<Client> clients;
    public ArrayList<Client> matchingClients = new ArrayList<Client>();
    private ServerSocket serverSocket;
    int port;
    
    public SocketManager(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
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
        
        private void inputInfo(String message){
            message = message.replaceAll("###", "");
            String[] info = message.split("/");
            String userInformation = info[6];
            
            User user = parseUser(info);
            
            Client newClient = clients.get(clients.size() - 1);
            newClient.setUser(user);
            newClient.getUser().setIsOnline(true);
            Debug.log("user : " + clients.get(clients.size() - 1).getUser());
        }
        
        private void matchingCheck(ArrayList<Client> matchingUser, Client client, int idx){
            if(client.isMatching() == 1 && !matchingUser.contains(client)){
                for(int i = 0; i< matchingUser.size(); i++){
                    if(matchingUser.get(i) == null){
                        matchingUser.set(i, client);
                        break;
                    } else {
                        matchingUser.add(client);
                        break;
                    }
                }
            }
            if(client.isMatching() == 2 && !matchingUser.contains(client)){
                for(int i = 0; i < matchingUser.size(); i++){
                    if(matchingUser.get(i) == null){
                        matchingUser.set(i, client);
                        break;
                    } else {
                        matchingUser.add(client);
                        break;
                    }
                }
            }
            if(client.isMatching() == 0 && matchingUser.contains(client)){
                matchingUser.set(idx, null);
            }
        }
        
        private void matchingStart(ArrayList<Client> clients, ArrayList<Client> matchingClients, Client client){
            for(int i = 0; i < clients.size(); i++){
                int toldx = 0;
                
                matchingCheck(matchingClients, client, i);
                if(clients.get(i).connectedClientIdx == -1){
                    if(matchingClients.get(i).status == 1){
                        
                        matchingClients.get(i).status = 2;
                        matchingClients.get(i).connectedClientIdx = (int) (Math.random() * clients.size());
                        toldx = matchingClients.get(i).connectedClientIdx;
                        matchingClients.get(toldx).connectedClientIdx = i;
                        matchingClients.get(toldx).status = 2;
                    }
                }
            }
        }
    }
    
    
    @Override
    public void run(){
        clients = new ArrayList<Client>();
        System.out.println("Server on");
        try{
            while(true){
                Socket socket = serverSocket.accept();
                Client connetedClient = new Client(socket);
                clients.add(connetedClient);

                connetedClient.println("누군가가 접속 했습니다");



                Thread t = new Thread(new ClientHandler(connetedClient.getClientSocket()));
                t.start();

                Debug.log("client ip : " + connetedClient.getClientSocket().getInetAddress());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public User parseUser(String[] info){
        User user = new User(info[0], info[1], info[2], info[3], info[4], Integer.parseInt(info[5]));
        return user;
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
