/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.vanier.HitTheTarget.ui;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class Projectile extends Application{

    public static ArrayList<Point2D> points;
    public Stage stage = new Stage();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        GUI u = new GUI();
        
        Scene scene = new Scene(u,975, 800);
        stage.setTitle("Projectile in Motion Path");
        stage.setScene(scene);
        stage.show();
    }   
}