/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maesh
 */
public class Database {
    String dbPath = "C:\\Users\\maesh\\OneDrive\\Documents\\NetBeansProjects\\HitTheTarget\\HitTheTarget\\app";
    
    String dbName = "HitTheTarget.db";
    
    String engine = "jdbc:sqlite";
    
    String connectionString = engine + ":" + dbPath + dbName;
    
    public Connection getConnection(){
        try{
            Connection dbConnection = DriverManager.getConnection(connectionString);
            return dbConnection;
        }catch(SQLException ex){
            System.out.println("An error Happened" + ex.getMessage());
            return null;
        }
    }
    
    
    
    
}
