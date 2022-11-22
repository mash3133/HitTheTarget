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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class MainAppController {
    
    //buttons
    @FXML
    Button btnPlay = new Button();
    @FXML
    Button btnPause = new Button();
    @FXML
    Button btnReplay = new Button();
    
    @FXML
    RadioButton marsGravity = new RadioButton();
    @FXML
    RadioButton earthGravity = new RadioButton();
    @FXML
    RadioButton moonGravity = new RadioButton();
    
    //textfield
    @FXML
    TextField initialVelocity = new TextField();
    @FXML
    TextField initialHeight = new TextField();
    @FXML
    TextField angle = new TextField();
    
    //toolbar
    @FXML
    MenuItem mnItemClose;
    
    //methods
    @FXML
    public void initializeInitialVelocity() {
        System.out.println("Initializing textfield initial velocity...");
        btnPlay.setOnAction((e1) -> {
            try {
                handleInitialVelocity(e1);
            } catch (IOException ex) {
                Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void handleInitialVelocity(ActionEvent e1) throws IOException {
    }
    
    @FXML
    private void handleCloseApp(ActionEvent event) {
        Platform.exit();
    }
}
