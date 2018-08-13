/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */

@Controller
@Scope("prototype")
public class UserAddFormController {
    
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    
    
    @FXML
    public void closeWindow(MouseEvent mouseEvent)
    {
       AnchorPane box=(AnchorPane)parent.getParent();
       box.getChildren().remove(parent);
    }
    
    @FXML
    public void windowDragged(MouseEvent mouseDragEvent)
    {
        AnchorPane box = (AnchorPane)parent.getParent();
        parent.setManaged(false);
        double newX=mouseDragEvent.getX()+parent.getTranslateX();
        double newY = mouseDragEvent.getY()+parent.getTranslateY();
        /*if(newX + parent.getBoundsInLocal().getWidth() >= box.getWidth() || 
                newY + parent.getBoundsInLocal().getHeight() >= box.getHeight())
            return;*/
        parent.setTranslateX(newX);
        parent.setTranslateY(newY);
        mouseDragEvent.consume();
    }
}
