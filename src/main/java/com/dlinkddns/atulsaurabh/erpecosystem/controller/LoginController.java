/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */
@Controller
public class LoginController 
{
    @Autowired
    private CustomFXMLLoader customFXMLLoader;
    
    @FXML
    private JFXTextField userNameTextBox;
    @FXML
    private JFXPasswordField passwordTextBox;
    
    @FXML
    public void performLogin(ActionEvent actionEvent)
    {
        Stage stage=customFXMLLoader.createStage(GUIInfo.GUI_DASHBOARD, "window.dashboard.title");
        stage.setMaximized(true);
        stage.show();
    }
    
    public void performReset(ActionEvent actionEvent)
    {
        userNameTextBox.setText("");
        passwordTextBox.setText("");
    }
}
