/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.math;

/**
 *
 * @author maesh
 */
public class MathMainApp {
    
    
    private double x,y,vx,vy,ax,ay,speed,time,angle,y0,vx0,vy0,distance;

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

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getVx0() {
        return vx0;
    }

    public void setVx0(double vx0) {
        this.vx0 = vx0;
    }

    public double getVy0() {
        return vy0;
    }

    public void setVy0(double vy0) {
        this.vy0 = vy0;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    public MathMainApp(double y, double angle, double speed, double gravity) 
    {
        this.y0=y;
        this.y = y;
        this.ax = 0;
        this.ay = gravity;
        this.angle=angle;
        this.speed = speed;
        this.vx = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy = this.speed*Math.sin(this.angle*(Math.PI/180.0));
        this.vx0 = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy0 = this.speed*Math.sin(this.angle*(Math.PI/180.0));
        
        this.time = (this.vy+Math.pow(Math.pow(this.vy, 2)+(4*0.5*this.ay*this.y), 0.5))/this.ay;
       
        this.distance=this.vx0*this.time;
    }
}