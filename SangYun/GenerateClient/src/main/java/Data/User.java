/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ssy02
 */
public class User {
    String id;
    boolean isCertificate;
    String nickname;
    
    
    public User(String id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }
}
