/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maesh
 */
public class DataBaseConnection {
    String dbPath = "C:\\Users\\maesh\\OneDrive\\Documents\\NetBeansProjects\\HitTheTarget\\HitTheTarget\\data\\";
    
    String dbName = "HitTheTarget.db";
    
    String engine = "jdbc:sqlite";
    
    String url = engine+":"+dbPath+dbName;
    
    public Connection dbConnection;
    
    public Connection getConnection(){
        try{
            dbConnection = DriverManager.getConnection(url);
            System.out.println("Connected to SQLITE");
            
        }catch(SQLException ex){
            System.out.println(ex);
            return null;
        }
        return dbConnection;
    }
    
}
