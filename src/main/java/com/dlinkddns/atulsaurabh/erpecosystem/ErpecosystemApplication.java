package com.dlinkddns.atulsaurabh.erpecosystem;

import com.dlinkddns.atulsaurabh.erpecosystem.logger.Logger;
import javafx.application.Application;
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

    private Stage primaryStage;

    public static void main(String[] args) {
        SpringApplication.run(ErpecosystemApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
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
        this.primaryStage = primaryStage;
        /*
           Integrating Spring Framework
           Loading the spring container. The spring container take care every
           object creation and other works in the system. Only GUI will be 
           designed using JAVAFX. 
         */
        SpringApplication.run(ErpecosystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
      /*
          This method will be required if some debugging information is required
          If the system need to check certain functionality through then it can
          be started in command line mode.
          But for now this functionality is not implemented.
      */  
      
      /*
         Startting the primary stage or window to interact the with the user.
      */   
    }
    
    
    
    
    
}
