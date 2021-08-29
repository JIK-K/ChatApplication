/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;
import Data.Client;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author JIK
 */
public class MatchingManager {
    //소켓 있잖음 ㅇ? IP받을수있다고 알잖아 우리 손님들 IP
    //그걸로 하면 되지않을까 근데
    //시이발 내가 그걸어떻게 알아
    //스레드 2개 있어야되는거 아님? 한명 한명씩 주고 2명이 되면 스레드 ON 해가지고
    //둘이서 쿵짝북짝 한다음에 "나가기" button 하면 close 하고 you nam SSang?
    ArrayList<Client> chatter = new ArrayList<Client>();
    int count = 0;
    BufferedReader reader;
    PrintWriter writer;
    
    public void randomUsermatching(){
        //랜덤 매칭
        if(count % 2 == 0){
            //chatter.start(); or t.start();
        }
    }
    
    public void premiumUsermatching(){
        //과금러 매칭
        if(count % 2 == 0){
            //chatter.start(); or t.start();
        }
    }
    
    
    public class matchingHandler implements Runnable{

        @Override
        public void run() {
            String message = "";
            try{
                while(!message.equals("bye")){
                    System.out.println("nickname 님과 매칭되었습니다");
                    message = reader.readLine();
                    System.out.println("받은 메세지" + "nickname" + ":" + message );
                    
                    //자신과 직접 연결된 클라이언트에게 메시지를 다시 전송한다
                    //this.sendMessage(nickname + ":" + message);
                    //1ㄷ1 채팅을 하도록 연결도니 클라이언트에게 메시지를 전송한다
                    //pair.sendMessage(nickname + ":" + message)
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                //close();
                System.out.println("이거 닫겨야 되는데 안닫기누");
            }
        }
    }

    public void go(){
        //여기서 thread 만들자
        //Client chatter = new Client(socket);
        Thread t = new Thread();
        t.start();
    }
    
    public void sendMessage(String message){
         //telleveryone 코드쓰면안될까?
         try{
             writer.println(message);
             writer.flush();
         }catch(Exception e){
             System.out.println(e.getMessage());
             System.out.println("sendMessage 예외발생");
         }
    }
}
