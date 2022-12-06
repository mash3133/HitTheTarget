/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.ui;

import edu.vanier.HitTheTarget.controllers.ProjectileController;
import edu.vanier.HitTheTarget.math.ProjectileMath;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class GUI extends GridPane{
    //1 layer
    private Menu settingsMenu = new Menu("Settings");
    private Menu helpMenu = new Menu("Help");
    //2nd layer
    private Menu GeneralM = new Menu("General");
    private Menu AnimationM = new Menu("Animation");
    private Menu ThemeM = new Menu("Theme");
    
    private MenuItem defaultSettings = new MenuItem("Default Values");
    private MenuItem dataBaseSettings = new MenuItem("DataBase Values");
    //general
    private MenuItem gravityEarth = new MenuItem("Earth gravity");
    private MenuItem gravitySun = new MenuItem("Sun gravity");
    private MenuItem gravityMars = new MenuItem("Mars gravity");
    //animation
    private MenuItem speedNormal = new MenuItem("Normal Speed");
    private MenuItem speedx2 = new MenuItem("x2 Speed");
    private MenuItem smallBall = new MenuItem("Small Ball");
    private MenuItem bigBall = new MenuItem("Big Ball");
    //theme
    private MenuItem blueBorder = new MenuItem("Blue Border Animation");
    private MenuItem redBorder = new MenuItem("Red Border Animation");
    private MenuItem redColorBall = new MenuItem("Red Ball");
    private MenuItem blueColorBall = new MenuItem("Blue Ball");
    
    private MenuItem helpItem = new MenuItem("Help");
    MenuBar mb = new MenuBar();
    
    public static Stage stage;
    public Scene scene;
    public static GridPane pane = new GridPane();
    public static Label helpLabel = new Label();
    String text;
    
    public BorderStroke bs;

    Label lb1 = new Label("Current gravity");
    Label lb2 = new Label("Current velocity");
    Label lb3 = new Label("Current height");
    Label lb4 = new Label("Current distance");

    public TextField f1 = new TextField();
    public TextField f3 = new TextField();
    public TextField f4 = new TextField();
    public TextField f5 = new TextField();
    
    Label lb11 = new Label("(m)");
    Label lb33 = new Label("(°)");
    Label lb44 = new Label("(m/s)");
    Label lb55 = new Label("(kg)");
   
    HBox hb11 = new HBox();
    HBox hb22 = new HBox();
    HBox hb33 = new HBox();
    HBox hb44 = new HBox();
    HBox hb55 = new HBox();
    
    
    public Button b1 = new Button("Start");
    public Button b2 = new Button("Pause");
    Button b3 = new Button("Reset");


    VBox vb = new VBox();
    VBox tableBar = new VBox();
    VBox vb1 = new VBox();
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    VBox vb2 = new VBox();
    VBox vb11 = new VBox();
    VBox vb21 = new VBox();
    VBox vb22 = new VBox();
    
    public Pane animation = new Pane();
    
    
    TableView<ProjectileMath>list = new TableView<ProjectileMath>();
    public ObservableList<ProjectileMath> items =FXCollections.observableArrayList();
        
    ProjectileController controller = new ProjectileController(this);

    public TextField getF1() {
        return f1;
    }

    public TextField getF3() {
        return f3;
    }

    public TextField getF4() {
        return f4;
    }

    public TextField getF5() {
        return f5;
    }

    public void setLb1(String lb1) {
        this.lb1.setText(lb1);
    }
    public void setLb2(String lb1) {
        this.lb2.setText(lb1);
    }
    public void setLb3(String lb1) {
        this.lb3.setText(lb1);
    }
    public void setLb4(String lb1) {
        this.lb4.setText(lb1);
    }
    
    
    
    
    public GUI() throws FileNotFoundException{
        
        
//        Set animation size
        bs = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null,
        new BorderWidths(2));
        Border border = new Border(bs);

        
        settingsMenu.getItems().addAll(GeneralM,AnimationM,ThemeM);
        GeneralM.getItems().addAll(gravityEarth,gravitySun,gravityMars,defaultSettings,dataBaseSettings);
        AnimationM.getItems().addAll(speedNormal,speedx2,smallBall,bigBall);
        ThemeM.getItems().addAll(blueColorBall,redColorBall,blueBorder,redBorder);
        helpMenu.getItems().addAll(helpItem);
        mb.getMenus().addAll(settingsMenu,helpMenu);
        text = "The goal of the animation is to create a projectile path according to the user's input values\nhelping "       
                + "the user to visualize the projectile path motion in 2D.\n\n--"
                + "boxes below or go to settings for default values\n2. Press the \"Start\" button after having the values in the text "
                + "boxes\n3. Press the \"Pause\" button to pause the projectile path\n4. Press the \"Resume\" button to continue the projectile "
                + "path\n5. Press the \"Reset\" button to clear the projectile path \n\nPlease note: Press the reset button before attempting a "
                + "new projectile's path. Also, press the \nreset button for any changes that are made.";
        helpLabel.setText(text);

        f1.promptTextProperty().setValue("Initial Height");
        f3.promptTextProperty().setValue("Angle");
        f4.promptTextProperty().setValue("Initial Velocity");
        f5.promptTextProperty().setValue("Mass");
        
        int bX, bY;
        bX = 75;
        bY = 25;
        b1.setMinSize(bX, bY);
        b2.setMinSize(bX, bY);
        b3.setMinSize(bX, bY);
        
        list.setItems(items);
        
        vb.setMinSize(975, 800);
        hb1.setMinSize(750, 500);
        hb2.setMinSize(750, 300);
        vb11.setMinSize(100, 500);
        animation.setMinSize(875, 500);
        animation.setBorder(border);
        vb21.setMinSize(185, 300);
        vb22.setMinSize(75, 300);
        list.setMinSize(715, 300);
        vb22.setAlignment(Pos.TOP_CENTER);
        
        TableColumn<ProjectileMath,Double>h=new TableColumn<>("Initial Height(m)");
        h.setCellValueFactory(new PropertyValueFactory<>("y0"));
        
        TableColumn<ProjectileMath,Double>g=new TableColumn<>("Gravity(m/s^2)");
        g.setCellValueFactory(new PropertyValueFactory<>("ay"));
        
        TableColumn<ProjectileMath,Double>a=new TableColumn<>("Angle(°)");
        a.setCellValueFactory(new PropertyValueFactory<>("angle"));
        
        TableColumn<ProjectileMath,Double>v=new TableColumn<>("Initial Velocity(m/s)");
        v.setCellValueFactory(new PropertyValueFactory<>("speed"));
        
        TableColumn<ProjectileMath,Double>x=new TableColumn<>("Distance(m)");
        x.setCellValueFactory(new PropertyValueFactory<>("distance"));
        
        TableColumn<ProjectileMath,Double>t=new TableColumn<>("Time(s)");
        t.setCellValueFactory(new PropertyValueFactory<>("time"));
        list.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        list.getColumns().addAll(h,g,a,v,x,t);
        //upperpart
        tableBar.getChildren().add(mb);
        vb11.getChildren().addAll(lb1,lb2,lb3,lb4);
        hb1.getChildren().addAll(vb11,animation);
        
        //lowerpart
        hb11.getChildren().addAll(f1,lb11);
        hb33.getChildren().addAll(f3,lb33);
        hb44.getChildren().addAll(f4,lb44);
        hb55.getChildren().addAll(f5,lb55);
        vb21.getChildren().addAll(hb11,hb22,hb33,hb44,hb55);
        vb22.getChildren().addAll(b1,b2,b3);
        hb2.getChildren().addAll(vb21,vb22,list);
        
        //whole interface
        vb1.getChildren().addAll(hb1);
        vb2.getChildren().addAll(hb2);
        vb.getChildren().addAll(tableBar,vb1,vb2);
        
        
        //Button actions
        
        b1.setOnAction(e -> controller.startEventHandler(e));
        
        b2.setOnAction(e-> controller.pauseEventHandler(e));
        
        b3.setOnAction(e-> controller.resetEventHandler(e));
        
        defaultSettings.setOnAction(e -> controller.defaultSettings());
        dataBaseSettings.setOnAction(e -> controller.dataBaseSettings());
        redColorBall.setOnAction(e -> controller.changeColorRedBall());
        blueColorBall.setOnAction(e -> controller.changeColorBlueBall());
        smallBall.setOnAction(e -> controller.smallBall());
        blueBorder.setOnAction(e -> controller.blueBorder());
        redBorder.setOnAction(e -> controller.redBorder());
        
        bigBall.setOnAction(e-> controller.bigBall());
        helpItem.setOnAction(e -> controller.help());
        
        
        gravityEarth.setOnAction(e -> controller.earthGravity());
        gravityMars.setOnAction(e -> controller.marsGravity());
        gravitySun.setOnAction(e -> controller.sunGravity());
        
        speedNormal.setOnAction(e -> controller.speedNormal());
        speedx2.setOnAction(e -> controller.speedx2());
        
        this.getChildren().addAll(vb);
    }  
}