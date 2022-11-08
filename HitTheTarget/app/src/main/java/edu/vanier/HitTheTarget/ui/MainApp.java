package edu.vanier.HitTheTarget.ui;

import edu.vanier.HitTheTarget.controllers.HitTheTargetController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author Sleiman Rabah.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/fxml/MenuWindow.fxml"));
        
        loader.setController(new HitTheTargetController());
        Pane root = loader.load();
        
        Scene scene = new Scene(root, 300, 600);
        stage.setScene(scene);
        stage.setTitle("Hit The Target");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}