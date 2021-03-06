/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.loader;

import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtility;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.dlinkddns.atulsaurabh.hasselfreelogger.api.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Atul Saurabh
 * @version 1.0
 */
public final class CustomFXMLLoader 
{
   @Autowired
   @Qualifier("basiclogger")
   private Logger logger;
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private ErpUtility erpUtility;
    
    
    public final NodeAndController loadFx(String guiName)
    {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setControllerFactory(context::getBean);
            fXMLLoader.setLocation(getClass().getResource(GUIInfo.GUI_HOME+guiName));
            NodeAndController nodeAndController = new NodeAndController();
            Parent parent=fXMLLoader.load();
            Object controller=fXMLLoader.getController();
            nodeAndController.setNode(parent);
            nodeAndController.setController(controller);
            return nodeAndController;
        } catch (Exception e) {
            logger.logFatal(this.getClass(), "Unable to load UI component. Kindly see the full stacktrace", e);
        }
        
        return null;
    }
    
    public final Parent load(String guiName)
    {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setControllerFactory(context::getBean);
            fXMLLoader.setLocation(getClass().getResource(GUIInfo.GUI_HOME+guiName));
            return fXMLLoader.load();
        } catch (IOException e) 
        {
           logger.logFatal(this.getClass(), "Unable to load UI component. Kindly see the full stacktrace", e);
        }
        return null;
    }
    
    
    public final Stage createStage(String UIName,String titleKey)
    {
        Stage stage  = createNewStage(titleKey, null);
        stage.setScene(new Scene(load(UIName)));
        return stage;
    }
    
    public final Stage createStage(String UIName,String titleKey,String iconKey)
    {
        Stage stage  = createNewStage(titleKey, iconKey);
        stage.setScene(new Scene(load(UIName)));
        return stage;
    }
    
    private final Stage createNewStage(String titleKey,String iconKey)
    {
    	Stage stage = new Stage();
    	stage.setTitle(erpUtility.resolvKey(titleKey));
    	if(iconKey != null)
    	{
    		String fileName = erpUtility.resolvKey(iconKey);
    		stage.getIcons().add(new Image(getClass().getResource(GUIInfo.IMAGE_HOME+fileName).toExternalForm()));
    	}
    	return stage;
    }
    
    
    public final Stage createStage(Parent parent,String titleKey)
    {
    	Stage stage = createNewStage(titleKey, null);
    	stage.setScene(new Scene(parent));
    	return stage;
    }
    
    public final Stage createStage(Parent parent,String titleKey,String iconKey)
    {
    	Stage stage = createNewStage(titleKey, iconKey);
    	stage.setScene(new Scene(parent));
    	return stage;
    }
    
    public final void setTitle(Stage stage,String key)
    {
        stage.setTitle(erpUtility.resolvKey(key));
    }
}
