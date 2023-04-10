/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author maham
 */
public class User {

    public int id;
    public String email;
    public String password;
    public String username;
    public Integer num_tel;
    public Date date_n;
    private String role;

 
    public boolean is_blocked;
    public boolean is_approved;

    public User(int id, String email, String password, String username, Integer num_tel, Date date_n,String role, boolean is_blocked, boolean is_approved) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.is_blocked = is_blocked;
        this.is_approved = is_approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNumtel() {
        return num_tel;
    }

    public void setNumtel(Integer numtel) {
        this.num_tel = numtel;
    }

    public Date getDate_n() {
        return date_n;
    }

    public void setDate_n(Date date_n) {
        this.date_n = date_n;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
       public Integer getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(Integer num_tel) {
        this.num_tel = num_tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}