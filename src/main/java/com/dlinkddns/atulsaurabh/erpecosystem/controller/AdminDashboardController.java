/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */
@Controller
public class AdminDashboardController implements Initializable
{
    @FXML
    private VBox sidepane;
 
    
    @Override
    public void initialize(URL url,ResourceBundle bundle)
    {
        
        for(Node node : sidepane.getChildren())
        {
            if(node.getAccessibleText() != null)
            {
                node.setOnMouseEntered((event) -> {
                   node.setStyle("-fx-background-color:#689f38");
                });
                node.setOnMouseExited((event) -> {
                    node.setStyle("-fx-background-color:#ffffff");
                });
            }
        }
        
    }
    
 /* @FXML
    public void changeBackgroundColor(MouseEvent mouseEvent)
    {
        HBox hbox= (HBox)mouseEvent.getSource();
       hbox.setStyle("-fx-background-color:#689f38");
    }
    
    @FXML
    public void resetBackgroundColor(MouseEvent mouseEvent)
    {
        HBox hbox= (HBox)mouseEvent.getSource();
       hbox.setStyle("-fx-background-color:#ffffff");
    }
*/
}
