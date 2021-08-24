/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;

/**
 *
 * @author ASUS
 */
public class Server {
    public void go(){
        try{
            ServerSocket serversocket = new ServerSocket(5000);
            Thread t = new Thread();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
