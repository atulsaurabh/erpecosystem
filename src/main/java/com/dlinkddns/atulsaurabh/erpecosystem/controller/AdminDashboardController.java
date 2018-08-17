/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.custom.ErpMenuBar;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.NodeAndController;
import com.dlinkddns.atulsaurabh.erpecosystem.plugin.PlugEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */
@Controller
public class AdminDashboardController implements Initializable {

    @FXML
    private ErpMenuBar adminMenuBar;
    @FXML
    private VBox sidepane;
    @FXML
    private AnchorPane container;

    @Autowired
    private CustomFXMLLoader customFXMLLoader;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        adminMenuBar.setPlugEventListener(plugEventListener -> {
            plugEventListener.handleEvent();
        });

        for (Node node : sidepane.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.setOnMouseEntered((event) -> {
                    node.setStyle("-fx-background-color:#689f38");
                    Menu menu = new Menu("Testing");
                    menu.getItems().add(new MenuItem("Test1"));
                    adminMenuBar.processPlugIn(menu);
                });
                node.setOnMouseExited((event) -> {
                    node.setStyle("-fx-background-color:#ffffff");
                });
            }
        }

    }

    @FXML
    public void showUserCreationForm(ActionEvent event) {
        NodeAndController nodeAndController = customFXMLLoader.loadFx(GUIInfo.GUI_CREATE_USER_FORM);
        UserAddFormController controller = (UserAddFormController) nodeAndController.getController();
        controller.setParent(nodeAndController.getParent());
        container.getChildren().add(nodeAndController.getParent());

    }

    @FXML
    public void openServiceRegistryForm(ActionEvent actionEvent) {
     NodeAndController nodeAndController=customFXMLLoader.loadFx(GUIInfo.GUI_PLUGIN_MANAGER_WINDOW);
     PluginManagerController pluginManagerController=(PluginManagerController)nodeAndController.getController();
     pluginManagerController.setParent(nodeAndController.getParent());
     container.getChildren().add(nodeAndController.getParent());

    }

}
