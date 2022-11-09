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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    Button btnPlay = new Button();

    @FXML
    public void initialize() {
        System.out.println("Initializing play button...");
        //mnItemClose.setOnAction(this::closeApplication);
        btnPlay.setOnAction((event) -> {
            try {
                startMainWindow(event);
            } catch (IOException ex) {
                Logger.getLogger(HitTheTargetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void startMainWindow(ActionEvent event) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(
                getClass().getResource("/fxml/MainApp_layout.fxml"));

        mainWindow.setController(new HitTheTargetController());

        Pane root = mainWindow.load();

        Scene scene = new Scene(root, 600, 600);
        Stage stage2 = new Stage();

        stage2.setScene(scene);
        stage2.setTitle("Projectile Simulation");
        stage2.setMaximized(false);
        stage2.show();
        
    }

}
