/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Network.ClientSocket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ssy02
 */
public class GenerateManager {
    int numberOfClient;
    
    public void getNumberOfClient(){
        System.out.println("클라이언트 수를 입력하세요.");
        numberOfClient = getInteger();
        setClients(numberOfClient);
        System.out.println("보낼 클라이언트");
        int sendIdx = getInteger();
        System.out.println("보낼 메세지");
        String message = getString();
        sendFrom(sendIdx, message);
        
    }
    
    public void setClients(int numOfClient){
        for(int i = 0; i < numOfClient; i++){
            ClientSocket cs = new ClientSocket();
            ClientSocketManager.getInstance().add(cs);
        }
    }
    
    public void sendFrom(int index, String message){
        System.out.println("client id : " + ClientSocketManager.getInstance().clients.get(index).clientName);
        ClientSocketManager.getInstance().clients.get(index).writer.println(ClientSocketManager.getInstance().clients.get(index).clientName + ":"+message);
    }
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    public static int getInteger(){
        int input = 0;
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                break;
            }catch (InputMismatchException exception) {
                System.out.println("정수만 입력 가능합니다..");
            }
        }
        return input;
    }
    //------------------------------------------------------------------------//
    //
    //------------------------------------------------------------------------//
    private String getString(){
        String input = "";
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextLine();
                break;
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return input;
    }
    
}
