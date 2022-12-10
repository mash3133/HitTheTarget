/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.math;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maesh
 */
public class SavedFileCSV {
    private static ObservableList savedFileList = FXCollections.observableArrayList();
    static private String filePath = null;
    
    public static void savedMathClass(MathMainApp mma, String fileName) throws IOException{
        savedFileList.add(fileName);
        filePath = "src/main/resources/savedFileCSV/" + fileName + ".csv";
        File file = new File(filePath);
        
        FileWriter outputfile = new FileWriter(file);
    }
    
    public static ObservableList getSavedFileList(){
        return savedFileList;
    }
    
    public static void setSavedFileList(ObservableList savedFileList){
        SavedFileCSV.savedFileList = savedFileList;
    }
    
}
