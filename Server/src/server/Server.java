/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import Network.SocketManager;
/**
 *
 * @author JIK
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocketManager socketMangaer = new SocketManager(5000);
    }
    
}