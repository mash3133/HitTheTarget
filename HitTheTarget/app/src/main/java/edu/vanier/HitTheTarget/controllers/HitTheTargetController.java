/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class HitTheTargetController {
    @FXML
    Button btnPlay = new Button();
    
    @FXML
    public void initialize(){
        System.out.println("Initializing play button...");
        btnPlay.setOnAction((event) -> {
            try {
                startMainWindow(event);
            } catch (IOException ex) {
                Logger.getLogger(HitTheTargetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @FXML
    public void startMainWindow(ActionEvent event) throws IOException{
        FXMLLoader mainWindow = new FXMLLoader(
        getClass().getResource("/fxml/main_Window_Projectile_Simulation.fxml"));
        
        mainWindow.setController(new HitTheTargetController());
        
        Pane root = mainWindow.load();
        
        Scene scene = new Scene(root, 600, 600);
        Stage stage2 = new Stage();
        
        stage2.setScene(scene);
        stage2.setTitle("Projectile Simulation");
        stage2.show();
    }
    
}
