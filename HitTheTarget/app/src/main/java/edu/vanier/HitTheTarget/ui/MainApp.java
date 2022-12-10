package edu.vanier.HitTheTarget.ui;

import edu.vanier.HitTheTarget.controllers.MainAppController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author
 */
public class MainApp extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Hit The Target");
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainApp_layout.fxml"));
        MainAppController mainController = new MainAppController(primaryStage);
        loader.setController(mainController);
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1500, 800);
        //scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static ArrayList<Point2D> points;
}