/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.hitthetarget.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maesh
 */
public class SQLiteDatabase {
    String dbPath = "C:C:\\Users\\maesh\\OneDrive\\Documents\\NetBeansProjects\\HitTheTarget\\HitTheTarget\\data";
    
    String dbName = "HitTheTarget.db";
    
    String engine = "jdbc:sqlite";
    
    String connectionString = engine + ":" + dbName;
    
    public Connection getConnection(){
        try{
            Connection dbConnection = DriverManager.getConnection(connectionString);
            return dbConnection;
        }catch(SQLException ex){
            System.out.println("An error Happened " + ex.getMessage());
            return null;
        }
    }   
}
