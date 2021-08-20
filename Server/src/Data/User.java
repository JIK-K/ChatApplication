/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author JIK
 */
public class User {
    private String name;
    private String user_id;
    private int user_password;
    private boolean gender;
    private String nickname;
    
    public User(String name, String id, int password, boolean gender, String nickname){
    this.name = name;
    this.user_id = id;
    this.user_password = password;
    this.gender = gender;
    this.nickname = nickname;
    }
}
