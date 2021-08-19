package Data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JIK
 */
public class Client {
    public ArrayList<User> user;
    
    
    String name;
    String user_id;
    int user_password;
    boolean gender;
    String nickname;
    
    void getUserInformation(){
        //user의 정보를 입력받는 메소드
        user = new ArrayList<User>();
        //@@@
        name = getString();
        //@@@
        User u = new User(name, user_id, user_password, gender, nickname);
        user.add(u);
        
        
    }
    
    public void matchingRandomUser(){
        //랜덤 매칭
        
        
        
    }
    
    public void matchingPremiumUser(){
        //과금러 매칭
        
        
        
    }
    
    public void checkUserGender(){
        //User 성별 체크 하는 메소드
        
        
        
    }
    
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
