/*package edu.vanier.HitTheTarget.controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    Button btnPlay = new Button();

    @FXML
    public void initialize() {
        System.out.println("Initializing play button...");
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
        stage2.setMaximized(true);
        stage2.show();
    }
    
    public void updateMousePosition(MouseEvent e, Text txtPosition) {
        txtPosition.setX(e.getX());
        txtPosition.setY(e.getY());
        txtPosition.setText("(" + e.getX() + ", " + e.getY() + ")");
        txtPosition.setVisible(true);
    }
}*/