package com.dlinkddns.atulsaurabh.erpecosystem;

import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ErpecosystemApplication extends Application implements ApplicationRunner{

    private static Stage primaryStage;
    
    @Autowired
    private CustomFXMLLoader customFXMLLoader;

    public static void main(String[] args) {
        Application.launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
        this.primaryStage=primaryStage;
       
        
        /*
             Retreiving all the command line parameters passed from the main method
        */
        String [] args = getParameters().getRaw().toArray(new String[getParameters().getRaw().size()]);
        /*
           The system operates in two different modes
           1) Command Mode
           2) GUI Mode
           The GUI should not be start immediately. Rather depending upon the 
           command line parameters the system will decide which mode it need 
           operate. But for the operation in GUI primaryStage should be saved
           and later on the basis of argument passed to the system this stage
           can be used.
        */
        
        /*
           Integrating Spring Framework
           Loading the spring container. The spring container take care every
           object creation and other works in the system. Only GUI will be 
           designed using JAVAFX. 
         */
        SpringApplication.run(ErpecosystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments  args) throws Exception {
      /*
          This method will be required if some debugging information is required
          If the system need to check certain functionality through then it can
          be started in command line mode.
          But for now this functionality is not implemented.
      */  
      
      /*
         Startting the primary stage or window to interact the with the user.
      */   
        //customFXMLLoader.setTitle(primaryStage, "window.login.title");
        if(args.containsOption("debug"))
        {
            
        }
        else
        {
            primaryStage = customFXMLLoader.createStage(GUIInfo.GUI_LOGIN, "window.login.title");
            Parent parent = customFXMLLoader.load(GUIInfo.GUI_LOGIN);
            primaryStage.setScene(new Scene(parent));
            primaryStage.setMaximized(false);
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }
    
    
    
    
    
}
