/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.controllers;

import edu.vanier.HitTheTarget.database.DBConnection;
import edu.vanier.HitTheTarget.math.ProjectileMath;
import edu.vanier.HitTheTarget.ui.GUI;
import static edu.vanier.HitTheTarget.ui.GUI.stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author maesh
 */
public class ProjectileController {
    private Polyline pl=new Polyline();
    private Circle qiu = new Circle();
    private PathTransition pt;
    private static GUI ui;
    private static ProjectileMath p;
    private static ArrayList<Polyline> plList;
    private static Timeline timeline;
    private double currentTime=0.0;
    private static ArrayList<Point2D> points;
    private static GridPane pane;
    private static DBConnection connectionProvider=new DBConnection();
    private static Connection connection=connectionProvider.getConnection();
    private static double gravity = 9.8;
    private static int size=3;
    private static Color color=Color.BLACK;
    private static Color color2=Color.BLUE;
    private static int speed=1;
    
    public ProjectileController(GUI view){
        
        ui = view;
        plList=new ArrayList<>();
        
    }
    
    
    public void startEventHandler(Event e)
    {
        if(Double.parseDouble(ui.getF3().getText())>0 && Double.parseDouble(ui.getF4().getText())>0 && Double.parseDouble(ui.getF5().getText())>0)
        {
            p = new ProjectileMath(Double.parseDouble(ui.getF1().getText()),Double.parseDouble(ui.getF3().getText()),Double.parseDouble(ui.getF4().getText()),gravity);
            points=p.getPoints();
            pt=new PathTransition(Duration.seconds(p.getTime()/speed), pl, qiu);
            ui.items.add(p);
            animate();
            time();
            ui.animation.getChildren().addAll(pl,qiu);
            pt.play();
            ui.b1.setDisable(true);
        
        }   else{
            ui.stage = new Stage();
            this.pane = new GridPane();
            
            this.pane.getChildren().add(new Label("Please enter positive value"));
            ui.scene = new Scene(pane, 510, 285);
            stage.setScene(ui.scene);
            stage.setTitle("Error");
            stage.show();
        }
            
        
    }
    public void pauseEventHandler(Event e) {
           if(ui.b2.getText().equals("Pause"))
           {    
               ui.b2.setText("Resume");
               timeline.pause();
               pt.pause();
           }
           else
           {
               ui.b2.setText("Pause");
               timeline.play();
               pt.play();
           }
    }
    
    public void resetEventHandler(Event e){
        
//        ui.pl = null;
        ui.animation.getChildren().clear();
        currentTime=0.0;
        timeline.stop();
        pt.stop();
        ui.setLb1("Current gravity");
        ui.setLb2("Current velocity");
        ui.setLb3("Current height");
        ui.setLb4("Current distance");
        pl.getPoints().clear();
        ui.b2.setText("Pause");
        ui.b1.setDisable(false);
    }
    
    void time()
    {
        timeline = new Timeline(
      new KeyFrame(Duration.seconds(0),
        new EventHandler<ActionEvent>() 
        {
          @Override public void handle(ActionEvent actionEvent) 
          {
              double t=currentTime();
              ui.setLb1(Double.toString(p.getAy())+"m/s^2");
              ui.setLb2(Double.toString(p.getCurrentV(t))+"m/s");
              if(p.getCurrentY(t)<0.1)
                  ui.setLb3(0+"m");
              else
                  ui.setLb3(Double.toString(p.getCurrentY(t))+"m");
              if(Math.abs(p.getCurrentX(t)-p.getDistance())<2)
                ui.setLb4(p.getDistance()+"m");
              else
                ui.setLb4(Double.toString(p.getCurrentX(t))+"m");
          }
        }
      ),
      new KeyFrame(Duration.seconds(0.03))
    );
        timeline.setCycleCount((int) (p.getTime()/0.03/speed)+1);
        timeline.play();
    }
    double currentTime()
    {
        currentTime=currentTime+speed*0.03;
        return currentTime;
    }
    void animate(){
        
        qiu.setCenterX(points.get(0).getX());
        qiu.setCenterY(500-points.get(0).getY());
        qiu.setRadius(size);
        qiu.setFill(color);
        
        
        points.forEach(p2 -> {
            pl.getPoints().addAll(p2.getX(), 500-p2.getY());
            
        });
        
        
        plList.add(pl);
        
        
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);           
        
        pt.interpolatorProperty().setValue(Interpolator.LINEAR);
    }
    
    
    public void defaultSettings()
    {
        ui.f1.setText("0");
        ui.f3.setText("55");
        ui.f4.setText("60");
        ui.f5.setText("10");
    }
    public void dataBaseSettings()
    {
        String query="SELECT * From AminiVenom";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            ui.f1.setText(String.valueOf(resultSet.getDouble("Initial Height")));
            ui.f3.setText(String.valueOf(resultSet.getDouble("Angle")));
            ui.f4.setText(String.valueOf(resultSet.getDouble("Initial Velocity")));
            ui.f5.setText(String.valueOf(resultSet.getDouble("Mass")));
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void help(){
        ui.stage = new Stage();
        this.pane = new GridPane();
        this.pane.getChildren().add(ui.helpLabel);
        ui.scene = new Scene(pane, 510, 285);
        stage.setScene(ui.scene);
        stage.setTitle("Help");
        stage.show();
    }
    

    
    public void changeColorRedBall(){
        qiu.setFill(color=Color.RED);
    }
    
    public void changeColorBlueBall(){
        qiu.setFill(color=Color.BLUE);
    }
    
    public void bigBall(){
        size=10;
    }
    public void smallBall(){
        size=3;
    }
    
    public void earthGravity(){
        gravity = 9.8;
    }
    public void marsGravity(){
        gravity = 3.7;
    }
    public void sunGravity(){
        gravity = 274;
    }
    
    public void blueBorder(){
        ui.bs = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null,
        new BorderWidths(2));
        Border border = new Border(ui.bs);
        ui.animation.setBorder(border);
    }
    
    public void redBorder(){
        ui.bs = new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null,
        new BorderWidths(2));
        Border border = new Border(ui.bs);
        ui.animation.setBorder(border);
    }
    
    public void speedNormal(){
        speed = 1;
    }
    public void speedx2(){
        speed = 2;
    }
    
}
