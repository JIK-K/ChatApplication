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

    public void setTargetClient() {
        System.out.println("메시지를 보낼 클라이언트 인덱스를 입력하세요.");
        int targetClientIdx = getInteger();
        String message = setMessage();

        sendFrom(targetClientIdx, message);
    }

    public void sendFrom(int idx, String msg){
        ClientSocket cs = ClientSocketManager.getInstance().clients.get(idx);
        String clientId = cs.getUser().getId();
        cs.getWriter().println(clientId + ":" + msg);
    }

    public String setMessage(){
        System.out.println("메시지를 입력하세요.");
        String message = getString();

        return message;
    }
    
    public void setNumOfClients(){
        System.out.println("클라이언트 수를 입력하세요.");
        numberOfClient = getInteger();
        for(int i = 0; i < numberOfClient; i++){
            ClientSocket cs = new ClientSocket();
            ClientSocketManager.getInstance().add(cs);
            
            
        }
        
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
