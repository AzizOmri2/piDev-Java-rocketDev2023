/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author lenovo
 */
public class MyDB {

    final String url="jdbc:mysql://localhost:3306/rocketdevdb4";
    final String username="root";
    final String pwd="";
    private Connection conx;
    
    public static MyDB instance;
    
    
    public static MyDB getInstance(){
        if (instance == null)
           instance = new MyDB();
        return instance;
        
    }
    private MyDB(){
        
        try {
            conx = DriverManager.getConnection(url, username, pwd); //Établissement de la connexion à la base de données MySQL.
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    public Connection getConx() {
        return conx;
    }
    
    
}
