/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;


import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GraphicsSupplier;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.NodeAndController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */
@Controller
public class AdminDashboardController implements Initializable {

    @FXML
    private MenuBar adminMenuBar;
    @FXML
    private VBox sidepane;
    @FXML
    private AnchorPane container;
    
    @FXML
    private MenuItem newUser,updateUser,deleteUser,activateUser;

    @Autowired
    private CustomFXMLLoader customFXMLLoader;
    
    @Autowired
    private GraphicsSupplier graphicsSupplier;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        
    	for (Node node : sidepane.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.setOnMouseEntered((event) -> {
                    node.setStyle("-fx-background-color:#689f38");
                });
                node.setOnMouseExited((event) -> {
                    node.setStyle("-fx-background-color:#ffffff");
                });
            }
        }
    	newUser.setGraphic(graphicsSupplier.iconGraphics("window.dashboard.new.menuitem.icon"));

    }

    @FXML
    public void showUserCreationForm(ActionEvent event) {
       NodeAndController nodeAndController = customFXMLLoader.loadFx(GUIInfo.GUI_CREATE_USER_FORM);
        UserAddFormController controller = (UserAddFormController) nodeAndController.getController();
        controller.setParent(nodeAndController.getNode());
       //container.getChildren().add(nodeAndController.getParent());
      Stage stage=customFXMLLoader.createStage(nodeAndController.getNode(), "window.useradd.title","window.useradd.icon");
      stage.setResizable(false);
      stage.setMaximized(false);
      stage.show();
    }

}
