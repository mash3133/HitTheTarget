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
public class DBConnection {
    //String dbPath="/Users/aminiwow/Documents/AminiVenom/data/";
    String dbPath="C:\\Users\\lekai\\Downloads\\AminiVenom(2)\\AminiVenom\\data\\";
    
    String dbName="AminiVenom.db";
    
    String engine="jdbc:sqlite";
    
    String connectionString=engine+":"+dbPath+dbName;
    
    public Connection getConnection()
    {   
    try
    {
        Connection dbConnection =DriverManager.getConnection(connectionString);
        
        return dbConnection;
    }catch(SQLException ex)
        {
            System.out.println("An Error Happeed:"+ex.getMessage());
         return null;
        }
    }
}