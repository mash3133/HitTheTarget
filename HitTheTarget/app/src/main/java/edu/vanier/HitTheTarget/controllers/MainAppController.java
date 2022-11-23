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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class MainAppController {
    //variable 
    private static final String RESOURCES_FOLDER = "";
    private static final String IMAGES_FOLDER = RESOURCES_FOLDER + "Images/";
    
    public static final String MARS_LANDSCAPE = IMAGES_FOLDER + "mars landscape.jpg";
    public static final String EARTH_LANDSCAPE = IMAGES_FOLDER + "earth landscape.jpg";
    public static final String MOON_LANDSCAPE = IMAGES_FOLDER + "moon landscape.jpg";

    
    //buttons
    @FXML
    Button btnPlay = new Button();
    @FXML      
    Button btnPause = new Button();
    @FXML
    Button btnReplay = new Button();
    
    @FXML
    ToggleGroup gravityOptions = new ToggleGroup();
    
    @FXML
    RadioButton marsGravity = new RadioButton();
    @FXML
    RadioButton earthGravity = new RadioButton();
    @FXML
    RadioButton moonGravity = new RadioButton();
    
    @FXML
    Pane pane = new Pane();
    
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
    
    public void chosenGravity(ActionEvent event){
        
        if(marsGravity.isSelected()){
            
            BackgroundImage marsImage = new BackgroundImage(new Image(MARS_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
            pane.setBackground(new Background(marsImage));
            
        } else if (earthGravity.isSelected()){
            
            pane.setStyle(EARTH_LANDSCAPE);
            
        } else if (moonGravity.isSelected()){
            
            pane.setStyle(MOON_LANDSCAPE);
            
        }
    }
}
