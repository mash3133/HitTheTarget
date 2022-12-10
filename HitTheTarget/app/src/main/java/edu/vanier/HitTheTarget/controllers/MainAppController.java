package edu.vanier.HitTheTarget.controllers;

import edu.vanier.HitTheTarget.math.MathMainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author maesh
 */
public class MainAppController {    

    //variables
    private Polyline poly = new Polyline();
    private Circle dot = new Circle();
    private PathTransition pt;
    private static MathMainApp mmp;
    private static ArrayList<Polyline> plList = new ArrayList<Polyline>();
    private static Timeline timeline = new Timeline();
    private double Time = 0.0;
    private static ArrayList<Point2D> points;
    public ObservableList<MathMainApp> items = FXCollections.observableArrayList();
    private static double gravity = 9.8;
    private static int size = 6;
    private static Color color = Color.BLACK;
    private static int speed = 1;
    protected int counter = 0;
    Stage Stage;
    
    private static final String RESOURCES_FOLDER = "";
    private static final String IMAGES_FOLDER = RESOURCES_FOLDER + "Images/";
    public static final String MARS_LANDSCAPE = IMAGES_FOLDER + "mars landscape.jpg";
    public static final String EARTH_LANDSCAPE = IMAGES_FOLDER + "earth landscape.jpg";
    public static final String MOON_LANDSCAPE = IMAGES_FOLDER + "moon landscape.jpg";
    public static final String JUPITER_LANDSCAPE = IMAGES_FOLDER + "jupiter landscape.jpg";
    
    //buttons
    @FXML
    Button btnStart = new Button();
    @FXML      
    Button btnPause = new Button();
    @FXML
    Button btnReplay = new Button();
    @FXML
    Button resumeBtn = new Button();
    @FXML
    Button saveBtn = new Button();
    @FXML
    ToggleGroup gravityOptions = new ToggleGroup();
    @FXML
    RadioButton mars = new RadioButton();
    @FXML
    RadioButton earth = new RadioButton();
    @FXML
    RadioButton moon = new RadioButton();
    @FXML
    RadioButton jupiter = new RadioButton();
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
    
    //Labels
    @FXML
    Label currentGravity = new Label();
    @FXML
    Label currentVelocity = new Label();
    @FXML
    Label currentDisplacement = new Label();

    //menu items

    @FXML
    Label mousePtLabel = new Label();
    @FXML
    MenuItem changeBallBlue;
    @FXML
    MenuItem changeBallRed;
    @FXML
    MenuItem changeBallYellow;
    @FXML
    MenuItem changeBallCyan;
    @FXML
    MenuItem changeBallBlackWhite;
    @FXML
    MenuItem changeBallBlueRed;
    @FXML
    MenuItem changeBallBrownGold;
    @FXML
    MenuItem mnItemAbout; 
    @FXML
    MenuItem smallBall; 
    @FXML
    MenuItem bigBall; 
    @FXML
    ListView listView;        
    
    //Alternating colors transitions
    FillTransition ft1 = new FillTransition(Duration.millis(900), dot, Color.BLACK, Color.WHITE);
    FillTransition ft2 = new FillTransition(Duration.millis(900), dot, Color.BLUE, Color.RED);
    FillTransition ft3 = new FillTransition(Duration.millis(900), dot, Color.BROWN, Color.GOLD);

//methods
    public void handleSaveBtn(ActionEvent event) throws IOException{
        Stage stage2 = new Stage();
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.initOwner(Stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SaveFile.fxml"));
        Stage saveName = new Stage();
        SaveFileController sfc = new SaveFileController(this, saveName);
        loader.setController(sfc);
        BorderPane root = loader.load();
        Scene SavePane = new Scene(root);
        stage2.setScene(SavePane);
        stage2.show();
    }
    
    public void mouseEventHandler(MouseEvent event){
        mousePtLabel.setTextFill(Color.BLACK);
        mousePtLabel.setAlignment(Pos.CENTER);
        mousePtLabel.setText("X = " + (event.getX()) + "     Y = " + (event.getY()));
    }
    
    //Change background depending on gravity chosen
    public void chosenGravity(ActionEvent event){
        
        if(mars.isSelected()){

            BackgroundImage marsImage = new BackgroundImage(new Image(MARS_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));           
            pane.setBackground(new Background(marsImage));
            gravity = 3.72; 
        } else if (earth.isSelected()){
            BackgroundImage earthImage = new BackgroundImage(new Image(EARTH_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));           
            pane.setBackground(new Background(earthImage));
            gravity = 9.81;
        } else if (moon.isSelected()){
            BackgroundImage moonImage = new BackgroundImage(new Image(MOON_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));           
            pane.setBackground(new Background(moonImage));
            gravity = 1.62;
            
        }else if (jupiter.isSelected()){
            
            BackgroundImage jupiterImage = new BackgroundImage(new Image(JUPITER_LANDSCAPE),BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0,1.0, true, true, false, false));           
            pane.setBackground(new Background(jupiterImage));
            gravity = 24.79;
            
        }
    }
    
    //Display current stats
    void displayStats()
    {
      timeline = new Timeline(
      new KeyFrame(Duration.seconds(0),
        new EventHandler<ActionEvent>() 
        {
          @Override public void handle(ActionEvent actionEvent) 
          {
              double t=currentTime();
              currentGravity.setText(Double.toString(mmp.getAy())+" m/s^2");
              currentVelocity.setText(Double.toString(mmp.getCurrentV(t))+" m/s");
              currentDisplacement.setText(Double.toString(mmp.getCurrentX(t))+" m");
              
          }
        }
      ),
      new KeyFrame(Duration.seconds(0.03))
    );
        timeline.setCycleCount((int) (mmp.getTime()/0.03/speed)+1);
        timeline.play();
    }
    
    
    //Plays the animation
   
    @FXML
    public void startEventHandler(Event e)
    {
        if(Double.parseDouble(angle.getText())>0 && Double.parseDouble(initialVelocity.getText())>0 && Double.parseDouble(initialHeight.getText())>0 && Double.parseDouble(initialHeight.getText())<760){
            mmp = new MathMainApp(Double.parseDouble(initialHeight.getText()),Double.parseDouble(angle.getText()),Double.parseDouble(initialVelocity.getText()),gravity);
            points = mmp.getPoints();
            pt = new PathTransition(Duration.seconds(mmp.getTime()/speed), poly, dot);
            items.add(mmp);
            animate();
            time();
            pane.getChildren().addAll(poly,dot);
            pt.play();
            listView.setItems(items);
            btnStart.setDisable(true);
            displayStats();
            
        }  
        
        if(Double.parseDouble(initialHeight.getText())>=760){
            Stage stage = new Stage();
            this.pane = new GridPane();
            Label lb = new Label();
            lb.setText("you cannot enter an initial height higher than 760");
            lb.setAlignment(Pos.CENTER);
            
            this.pane.getChildren().add(lb);
            
            Scene scene = new Scene(pane, 300, 300);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
            
            pane.getChildren().clear();
            Time = 0.0;
            timeline.stop();
            pt.stop();
            poly.getPoints().clear();
            btnStart.setDisable(false);
        }
        
        if(Double.parseDouble(initialHeight.getText())<0){
            Stage stage = new Stage();
            this.pane = new GridPane();
            Label lb = new Label();
            lb.setText("you must enter a positive initial height");
            lb.setAlignment(Pos.CENTER);
            
            this.pane.getChildren().add(lb);
            
            Scene scene = new Scene(pane, 300, 300);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
            
            pane.getChildren().clear();
            Time = 0.0;
            timeline.stop();
            pt.stop();
            poly.getPoints().clear();
            btnStart.setDisable(false);
        }
        
        if(Double.parseDouble(initialVelocity.getText())<=0){
            Stage stage = new Stage();
            this.pane = new GridPane();
            Label lb = new Label();
            lb.setText("Please enter a positive velocity");
            lb.setAlignment(Pos.CENTER);
            
            this.pane.getChildren().add(lb);
            
            Scene scene = new Scene(pane, 300, 300);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
            
            pane.getChildren().clear();
            Time = 0.0;
            timeline.stop();
            pt.stop();
            poly.getPoints().clear();
            btnStart.setDisable(false);
        }
        
        if(Double.parseDouble(angle.getText())<=0){
            Stage stage = new Stage();
            this.pane = new GridPane();
            Label lb = new Label();
            lb.setText("Please enter a positive angle");
            lb.setAlignment(Pos.CENTER);
            
            this.pane.getChildren().add(lb);
            
            Scene scene = new Scene(pane, 300, 300);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();
            
            pane.getChildren().clear();
            Time = 0.0;
            timeline.stop();
            pt.stop();
            poly.getPoints().clear();
            btnStart.setDisable(false);
        }
    }
    
    
    
    //Pauses animation
    @FXML
    public void pauseEventHandler(Event e) {
        btnStart.setDisable(false);   
        timeline.pause();
        pt.pause();
    }
    
    //Resumes animation
    @FXML
    public void resumeEventHandler(Event e) {
        timeline.play();
        pt.play();
    }
    
    //Resets animation 
    @FXML
    public void resetEventHandler(Event e){
        pane.getChildren().clear();
        Time = 0.0;
        timeline.stop();
        pt.stop();
        poly.getPoints().clear();
        btnStart.setDisable(false);
    }
    
    //animation
    public void time(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
            double t = currentTime();
            if(Math.abs(mmp.getCurrentX(t)-mmp.getDistance())<2);
        }),
      new KeyFrame(Duration.seconds(0.03))
        );
        timeline.setCycleCount((int) (mmp.getTime()/0.03/speed)+1);
        timeline.play();
    }
    
    double currentTime(){
        Time=Time+speed*0.03;
        return Time;
    }
    
    public void animate(){
        dot.setCenterX(points.get(0).getX());
        dot.setCenterY(100-points.get(0).getY());
        dot.setRadius(size);
        dot.setFill(color);
        points.forEach(p2 -> {
            poly.getPoints().addAll(p2.getX(), 780-p2.getY());
        });
        plList.add(poly);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);  
        pt.interpolatorProperty().setValue(Interpolator.LINEAR);
    }
    
    @FXML
    private void handleCloseApp(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    public void aboutBtnEventHandler(ActionEvent event){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Important information");
        info.setContentText("""
                            
                            The goal of the animation is to recreate a projectile's path according to the user's input values
                            with the purpose of helping the user visualize the motion of a projectile in 2D.
                            
                            -- How to use the animation
                            1. Input all values in the text boxes on the right
                            2. Select one value for the gravity
                            3. Press the "Start" button after having the values in the text boxes
                            4. Press the "Pause" button to pause the projectile path
                            5. Press the "Resume" button to continue the projectile path
                            6. Press the "Replay" button to clear the projectile path 
                            
                            Note: The default color of the projectile is black. It can be changed in settings

                                                      
           
                            
                           
                            """);
        info.show();
        info.setResizable(true);
    }
    

    //Change the color of the ball methods
    public void handleBlueColor(){
        dot.setFill(color = Color.BLUE);
    }
    
    public void handleRedColor(){
        dot.setFill(color = Color.RED);
    }
    
    public void handleYellowColor(){
        dot.setFill(color = Color.YELLOW);
    }
    
    public void handleCyanColor(){
        dot.setFill(color = Color.CYAN);
    }

    
    public void handleBlackAndWhite(){
        ft1.setCycleCount(Timeline.INDEFINITE);
        ft1.setAutoReverse(true);
        ft1.play();

    }
    
    public void handleBlueAndRed(){
        ft2.setCycleCount(Timeline.INDEFINITE);
        ft2.setAutoReverse(true);
        ft2.play();

    }
    
    public void handleBrownAndGold(){
        ft3.setCycleCount(Timeline.INDEFINITE);
        ft3.setAutoReverse(true);
        ft3.play();

    }
    
    //Change size of the ball methods
    
    public void handleBigBall(){
        size = 9;
    }
    
    public void handleSmallBall(){
        size = 5;
    }
    
    //Display displacement of ball method
    //mutators
    public Polyline getPl() {
        return poly;
    }

    public void setPl(Polyline pl) {
        this.poly = pl;
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
        return Time;
    }

    public void setCurrentTime(double currentTime) {
        this.Time = currentTime;
    }

    public static ArrayList<Point2D> getPoints() {
        return points;
    }

    public static void setPoints(ArrayList<Point2D> points) {
        MainAppController.points = points;
    }
    
    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        MainAppController.size = size;
    }

    public static Color getColor() {
        return color;
    }

    public static void setBlack(Color black) {
        MainAppController.color = black;
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

    public Polyline getPoly() {
        return poly;
    }

    public void setPoly(Polyline poly) {
        this.poly = poly;
    }

    public ObservableList<MathMainApp> getItems() {
        return items;
    }

    public void setItems(ObservableList<MathMainApp> items) {
        this.items = items;
    }

    public static double getGravity() {
        return gravity;
    }

    public static void setGravity(double gravity) {
        MainAppController.gravity = gravity;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(Button btnStart) {
        this.btnStart = btnStart;
    }

    public Button getResumeBtn() {
        return resumeBtn;
    }

    public void setResumeBtn(Button resumeBtn) {
        this.resumeBtn = resumeBtn;
    }

    public ToggleGroup getGravityOptions() {
        return gravityOptions;
    }

    public void setGravityOptions(ToggleGroup gravityOptions) {
        this.gravityOptions = gravityOptions;
    }

    public RadioButton getMars() {
        return mars;
    }

    public void setMars(RadioButton mars) {
        this.mars = mars;
    }

    public RadioButton getEarth() {
        return earth;
    }

    public void setEarth(RadioButton earth) {
        this.earth = earth;
    }

    public RadioButton getMoon() {
        return moon;
    }

    public void setMoon(RadioButton moon) {
        this.moon = moon;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Label getMousePtLabel() {
        return mousePtLabel;
    }

    public void setMousePtLabel(Label mousePtLabel) {
        this.mousePtLabel = mousePtLabel;
    }   

    public double getTime() {
        return Time;
    }

    public void setTime(double Time) {
        this.Time = Time;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public RadioButton getJupiter() {
        return jupiter;
    }

    public void setJupiter(RadioButton jupiter) {
        this.jupiter = jupiter;
    }

    public MenuItem getChangeBallBlue() {
        return changeBallBlue;
    }

    public void setChangeBallBlue(MenuItem changeBallBlue) {
        this.changeBallBlue = changeBallBlue;
    }

    public MenuItem getChangeBallRed() {
        return changeBallRed;
    }

    public void setChangeBallRed(MenuItem changeBallRed) {
        this.changeBallRed = changeBallRed;
    }

    public MenuItem getChangeBallYellow() {
        return changeBallYellow;
    }

    public void setChangeBallYellow(MenuItem changeBallYellow) {
        this.changeBallYellow = changeBallYellow;
    }

    public MenuItem getChangeBallCyan() {
        return changeBallCyan;
    }

    public void setChangeBallCyan(MenuItem changeBallCyan) {
        this.changeBallCyan = changeBallCyan;
    }

    public MenuItem getMnItemAbout() {
        return mnItemAbout;
    }

    public void setMnItemAbout(MenuItem mnItemAbout) {
        this.mnItemAbout = mnItemAbout;
    }
    
    
}
