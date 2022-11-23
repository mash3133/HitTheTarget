/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.vanier.HitTheTarget.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class Driver extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        //drawing 
        /*
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 500, 500);
        Polyline pl = new Polyline();

        scene.setOnMousePressed(event -> {
            root.getChildren().add(pl);
        });

        scene.setOnMouseDragged(event -> {
            pl.getPoints().addAll(event.getX(), event.getY());
        });

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }
}