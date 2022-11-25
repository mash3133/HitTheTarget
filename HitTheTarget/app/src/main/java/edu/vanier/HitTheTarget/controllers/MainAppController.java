package edu.vanier.HitTheTarget.controllers;

import database.Database;
import edu.vanier.HitTheTarget.math.MathMainApp;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class MainAppController {    
    //variable 
    private Polyline pl = new Polyline();
    private Circle dot = new Circle();
    private PathTransition pt;
    
    private static MathMainApp mmp;
    private static ArrayList<Polyline> plList;
    private static Timeline timeline;
    private double currentTime = 0.0;
    private static ArrayList<Point2D> points;
    
    private static Database connectionProvider = new Database();
    private static Connection connection = connectionProvider.getConnection();
    private static double marsGravity = 3.72;
    private static double earthGravity = 9.81;
    private static double moonGravity = 1.62;
    private static int size = 4;
    private static Color black = Color.BLACK;
    private static int speed = 1;
    
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
    RadioButton mars = new RadioButton();
    @FXML
    RadioButton earth = new RadioButton();
    @FXML
    RadioButton moon = new RadioButton();
    
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
    
    //mutators

    public Polyline getPl() {
        return pl;
    }

    public void setPl(Polyline pl) {
        this.pl = pl;
    }

    public Circle getDot() {
        return dot;
    }

    public void setDot(Circle dot) {
        this.dot = dot;
    }

    public PathTransition getPt() {
        return pt;
    }

    public void setPt(PathTransition pt) {
        this.pt = pt;
    }

    public static MathMainApp getMmp() {
        return mmp;
    }

    public static void setMmp(MathMainApp mmp) {
        MainAppController.mmp = mmp;
    }

    public static ArrayList<Polyline> getPlList() {
        return plList;
    }

    public static void setPlList(ArrayList<Polyline> plList) {
        MainAppController.plList = plList;
    }

    public static Timeline getTimeline() {
        return timeline;
    }

    public static void setTimeline(Timeline timeline) {
        MainAppController.timeline = timeline;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public static ArrayList<Point2D> getPoints() {
        return points;
    }

    public static void setPoints(ArrayList<Point2D> points) {
        MainAppController.points = points;
    }

    public static Database getConnectionProvider() {
        return connectionProvider;
    }

    public static void setConnectionProvider(Database connectionProvider) {
        MainAppController.connectionProvider = connectionProvider;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        MainAppController.connection = connection;
    }

    public static double getMarsGravity() {
        return marsGravity;
    }

    public static void setMarsGravity(double marsGravity) {
        MainAppController.marsGravity = marsGravity;
    }

    public static double getEarthGravity() {
        return earthGravity;
    }

    public static void setEarthGravity(double earthGravity) {
        MainAppController.earthGravity = earthGravity;
    }

    public static double getMoonGravity() {
        return moonGravity;
    }

    public static void setMoonGravity(double moonGravity) {
        MainAppController.moonGravity = moonGravity;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        MainAppController.size = size;
    }

    public static Color getBlack() {
        return black;
    }

    public static void setBlack(Color black) {
        MainAppController.black = black;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        MainAppController.speed = speed;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    public Button getBtnPause() {
        return btnPause;
    }

    public void setBtnPause(Button btnPause) {
        this.btnPause = btnPause;
    }

    public Button getBtnReplay() {
        return btnReplay;
    }

    public void setBtnReplay(Button btnReplay) {
        this.btnReplay = btnReplay;
    }

    public TextField getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(TextField initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public TextField getInitialHeight() {
        return initialHeight;
    }

    public void setInitialHeight(TextField initialHeight) {
        this.initialHeight = initialHeight;
    }

    public TextField getAngle() {
        return angle;
    }

    public void setAngle(TextField angle) {
        this.angle = angle;
    }

    public MenuItem getMnItemClose() {
        return mnItemClose;
    }

    public void setMnItemClose(MenuItem mnItemClose) {
        this.mnItemClose = mnItemClose;
    }
    
    
    //Change background depending on gravity chosen
    public void chosenGravity(ActionEvent event){
        
        if(mars.isSelected()){

            BackgroundImage marsImage = new BackgroundImage(new Image(MARS_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));
            
            pane.setBackground(new Background(marsImage));
            
        } else if (earth.isSelected()){

            BackgroundImage earthImage = new BackgroundImage(new Image(EARTH_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));
            pane.setBackground(new Background(earthImage));
            pane.setStyle(EARTH_LANDSCAPE);
            
        } else if (moon.isSelected()){

            BackgroundImage moonImage = new BackgroundImage(new Image(MOON_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
            pane.setBackground(new Background(moonImage));
            pane.setStyle(MOON_LANDSCAPE);
            
        }
        
    //Take screenshots button
    }
}
