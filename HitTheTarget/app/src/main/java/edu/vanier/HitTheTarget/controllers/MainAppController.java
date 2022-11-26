package edu.vanier.HitTheTarget.controllers;

import edu.vanier.hitthetarget.database.SQLiteDatabase;
import edu.vanier.HitTheTarget.math.MathMainApp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    public ObservableList<MathMainApp> items = FXCollections.observableArrayList();
    
    private static SQLiteDatabase connectionProvider = new SQLiteDatabase();
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
    Button btnStart = new Button();
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
    
    @FXML
    Circle circle = new Circle();
    private double x;
    private double y;
    
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
    
    //methods for hit the target
    //position circle
    
    @FXML
    public void initialize() {
        System.out.println("Initializing start button...");
        btnStart.setOnAction((event) -> {
            start(event);
        }
        );
    }
    
    @FXML
    public void start(ActionEvent event){
        System.out.println("start button was pressed");
        circle.setCenterX(0);
        try{
            circle.setCenterY((Double.parseDouble(getInitialHeight().getText()))* -1 - 30);
        
            if(Double.parseDouble(getInitialHeight().getText())<0 && (Double.parseDouble(getInitialHeight().getText()))>500){
            System.out.println("enter a positive value");
            }
        }
        catch(java.lang.NumberFormatException e){
            Stage stage = new Stage();
            this.pane = new GridPane();
            Label lbl = new Label();
            
            this.pane.getChildren().add(new Label("Please enter positive value"));
            Scene scene = new Scene(pane, 510, 285);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
        }
        catch(Exception e){
            System.out.println(e);
}
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
    }
    //Take screenshots button
    
    
    
    //methods for projectile 
    public void startEventHandler(Event e)
    {
        if(Double.parseDouble(getAngle().getText())>0 && Double.parseDouble(getInitialVelocity().getText())>0)
        {
            mmp = new MathMainApp(Double.parseDouble(getInitialHeight().getText()),Double.parseDouble(getAngle().getText()),Double.parseDouble(getInitialVelocity().getText()),earthGravity);
            points = mmp.getPoints();
            pt = new PathTransition(Duration.seconds(mmp.getTime()/speed), pl, dot);
            items.add(mmp);
            animate();
            time();
            pane.getChildren().addAll(pl,dot);
            pt.play();
            btnStart.setDisable(true);
        
        }   else{
            Stage stage = new Stage();
            this.pane = new GridPane();
            
            this.pane.getChildren().add(new Label("Please enter positive value"));
            Scene scene = new Scene(pane, 510, 285);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
        }
            
        
    }
    public void pauseEventHandler(Event e) {
           if(btnPause.getText().equals("Pause"))
           {    
               btnPause.setText("Resume");
               timeline.pause();
               pt.pause();
           }
           else
           {
               btnPause.setText("Pause");
               timeline.play();
               pt.play();
           }
    }
    
    public void resetEventHandler(Event e){
        
        pane.getChildren().clear();
        currentTime=0.0;
        timeline.stop();
        pt.stop();
        pl.getPoints().clear();
        btnStart.setDisable(false);
    }
    
    void time(){
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>()
                        {
                            @Override public void handle(ActionEvent actionEvent)
                            {
                                double t=currentTime();
                                if(Math.abs(mmp.getCurrentX(t)-mmp.getDistance())<2);
                            }
                        }
                ),
      new KeyFrame(Duration.seconds(0.03))
    );
        timeline.setCycleCount((int) (mmp.getTime()/0.03/speed)+1);
        timeline.play();
    }
    double currentTime()
    {
        currentTime=currentTime+speed*0.03;
        return currentTime;
    }
    void animate(){
        
        dot.setCenterX(points.get(0).getX());
        dot.setCenterY(500-points.get(0).getY());
        dot.setRadius(size);
        dot.setFill(black);
        
        
        points.forEach(p2 -> {
            pl.getPoints().addAll(p2.getX(), 500-p2.getY());
            
        });
        
        
        plList.add(pl);
        
        
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);           
        
        pt.interpolatorProperty().setValue(Interpolator.LINEAR);
    }
    
    public void dataBaseSettings()
    {
        String query = "SELECT * From HitTheTarget";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            initialHeight.setText(String.valueOf(resultSet.getDouble("Initial Height")));
            angle.setText(String.valueOf(resultSet.getDouble("Angle")));
            initialVelocity.setText(String.valueOf(resultSet.getDouble("Initial Velocity")));
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
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

    public static SQLiteDatabase getConnectionProvider() {
        return connectionProvider;
    }

    public static void setConnectionProvider(SQLiteDatabase connectionProvider) {
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
        return btnStart;
    }

    public void setBtnPlay(Button btnPlay) {
        this.btnStart = btnPlay;
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
}
