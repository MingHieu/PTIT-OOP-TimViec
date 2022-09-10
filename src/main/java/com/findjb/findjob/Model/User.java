package com.findjb.findjob.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username") 
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "user_type")
    private int user_type;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUser_type() {
        return user_type;
    }
    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
