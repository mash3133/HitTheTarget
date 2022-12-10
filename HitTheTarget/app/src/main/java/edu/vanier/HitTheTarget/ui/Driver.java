/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.vanier.HitTheTarget.ui;

import edu.vanier.HitTheTarget.controllers.MainAppController;
import edu.vanier.HitTheTarget.controllers.MenuAppController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class Driver extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuWindow3.fxml"));
            MenuAppController menuAppController = new MenuAppController(primaryStage);
            loader.setController(menuAppController);
            
            BorderPane root = loader.load();
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static ArrayList<Point2D> points;
}