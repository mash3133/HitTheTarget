package edu.vanier.HitTheTarget.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class MainAppController {
    
    @FXML
    Button btnPlay = new Button();
    @FXML
    MenuItem mnItemClose;
    @FXML
    TextField initialVelocity = new TextField();
    @FXML
    TextField initialHeight = new TextField();
    @FXML
    TextField angle = new TextField();
    
    
    public void initialize() {
        System.out.println("Initializing play button...");
        //mnItemClose.setOnAction(this::closeApplication);
        btnPlay.setOnAction((event) -> {
            try {
                startMainWindow(event);
            } catch (IOException ex) {
                Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void startMainWindow(ActionEvent event) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(
                getClass().getResource("/fxml/MainApp_layout.fxml"));

        mainWindow.setController(new MainAppController());

        Pane root = mainWindow.load();

        Scene scene = new Scene(root, 600, 600);
        Stage stage2 = new Stage();

        stage2.setScene(scene);
        stage2.setTitle("Projectile Simulation");      
        stage2.setFullScreen(true);
        stage2.show();
    }

    @FXML
    private void handleCloseApp(ActionEvent event) {
        Platform.exit();
    }
}
