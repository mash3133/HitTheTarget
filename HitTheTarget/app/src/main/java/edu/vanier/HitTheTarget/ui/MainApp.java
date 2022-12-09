package edu.vanier.HitTheTarget.ui;

import edu.vanier.HitTheTarget.controllers.MainAppController;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    
    public static ArrayList<Point2D> points;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/fxml/MainApp_layout.fxml"));
        
        loader.setController(new MainAppController());
        Pane root = loader.load();
        Scene scene = new Scene(root, 1500, 800);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setTitle("Hit The Target");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}