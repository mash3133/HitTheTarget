/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.HitTheTarget.controllers;

import edu.vanier.HitTheTarget.math.MathMainApp;
import edu.vanier.HitTheTarget.math.SavedFileCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class SaveFileController {
    MainAppController mac;
    Stage stage;
    MathMainApp mmp;

    public SaveFileController(MainAppController mac, Stage stage) {
        this.mac = mac;
        this.stage = stage;
    }
    
    @FXML
    Button saveBtn = new Button();
    @FXML
    Label saveLbl = new Label();
    @FXML
    TextField saveTf = new TextField();
    
    public void handleSave(ActionEvent event){
        String fileName = saveTf.getText();
        
        if(SavedFileCSV.getSavedFileList().contains(fileName)){
            
        }
    }

    public MainAppController getMac() {
        return mac;
    }

    public void setMac(MainAppController mac) {
        this.mac = mac;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public MathMainApp getMmp() {
        return mmp;
    }

    public void setMmp(MathMainApp mmp) {
        this.mmp = mmp;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public Label getSaveLbl() {
        return saveLbl;
    }

    public void setSaveLbl(Label saveLbl) {
        this.saveLbl = saveLbl;
    }

    public TextField getSaveTf() {
        return saveTf;
    }

    public void setSaveTf(TextField saveTf) {
        this.saveTf = saveTf;
    }
    
    
}
