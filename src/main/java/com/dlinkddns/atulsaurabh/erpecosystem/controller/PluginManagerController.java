
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Suyojan
 */

@Controller
public class PluginManagerController 
{
   @FXML
    private JFXTextField pluginName;

    @FXML
    private ToggleGroup enable_disable;
    
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    

    @FXML
    public void selectPlugin(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR FILES", "*.jar"));
        fileChooser.setTitle("Select a Plugin");
        File pluginFile = fileChooser.showOpenDialog(parent.getScene().getWindow());
        if(pluginFile != null)
        {
            try {
                String pluginPath = pluginFile.getName();
                pluginName.setText(pluginPath);
            } catch (Exception e) {
            }
           
        }
        else
        {
                        
        }
    }
    @FXML
    public void closeWindow(MouseEvent mouseEvent)
    {
        AnchorPane box=(AnchorPane)parent.getParent();
       box.getChildren().remove(parent);
    }
    
}
