/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.math;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author maesh
 */
public class ProjectileMath {
    private final double dTime=0.001;
    private double x,y,vx,vy,ax,ay,speed,time,angle,y0,vx0,vy0,distance;
    private ArrayList<Point2D> points;
    public ProjectileMath(double y, double angle, double speed, double gravity) 
    {
        this.y0=y;
        this.y = y;
        this.ax = 0;
        this.ay = gravity;
        this.angle=angle;
        this.speed = speed;
        this.points=new ArrayList<>();
        this.vx = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy = this.speed*Math.sin(this.angle*(Math.PI/180.0));
        this.vx0 = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy0 = this.speed*Math.sin(this.angle*(Math.PI/180.0));
        
        this.time = (this.vy+Math.pow(Math.pow(this.vy, 2)+(4*0.5*this.ay*this.y), 0.5))/this.ay;
        this.time=SF(this.time,3);
        this.distance=this.vx0*this.time;
        this.distance=SF(this.distance,3);
    }
    
    public double SF(double value,int point)
    {
        value=value*Math.pow(10, point);
        value=Math.floor(value);
        value=value/Math.pow(10, point);
        return value;
    }
    public double getCurrentV(double t)
    {   
        
        return SF(Math.pow(Math.pow(this.vx0, 2)+Math.pow(this.vy0-this.ay*t, 2), 0.5),3);
    }
    public double getCurrentX(double t)
    {   
        return SF(this.vx0*t,3);
    }
    public double getCurrentY(double t)
    {   
        return SF(this.y0+(this.vy0*t-0.5*this.ay*t*t),3);
    }
    public double getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }

    public double getY0() {
        return y0;
    }
    
    
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    
    public ArrayList getPoints()
    {
        double currentTime = 0.0;
        while (currentTime < this.time)
            {
            currentTime += this.dTime;
            this.x += this.vx*this.dTime;
            this.y += this.vy*this.dTime;
            this.points.add(new Point2D(this.x, this.y));
            this.vx += this.ax*this.dTime;
            this.vy -= this.ay*this.dTime;
            }
        return this.points; 
    } 
}