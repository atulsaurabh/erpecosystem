/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Value;

/**
 *@version 1.0
 * @author Atul Saurabh
 * 
 */

public class ErpEcosystemLogger implements Logger
{
   @Value("${erp.log.directory}")
   private String log_directory_name;
   
   @Value("${erp.log.baselogfilename}")
   private String log_file_name;
   
   private String conversionPattern;
   
   @Getter
   @Setter
   private boolean rollingOn=false;
   @Value("dd-MM-yyyy")
   @Getter
   @Setter
   private String datePattern;
   
    /**
     * 
     * @param message The fatal level message to be logged in
     * 
     */
    @Override
    public void logFatal(String message)
    {
      org.apache.log4j.Logger logger=log(null, Level.FATAL);
      logger.fatal(message);
    }

    /**
     * 
     * @param errorClass  The class where the exception is generated
     * @param message The fatal level message to be logged in
     */
    @Override
    public void logFatal(Class errorClass, String message) {
        org.apache.log4j.Logger logger=log(errorClass, Level.FATAL);
        logger.fatal(message);
    }
    
    /**
     * 
     * @param errorClass The class where the exception is generated
     * @param message The fatal level message to be logged in
     * @param throwable The exception to be logged in 
     */

    @Override
    public void logFatal(Class errorClass, String message, Throwable throwable) {
      org.apache.log4j.Logger logger=log(errorClass, Level.FATAL);
        logger.fatal(message,throwable);  
    }
    
    /**
     * 
     * @param message The message to be logged in. The logging will be done for 
     *                every level.
     */

    @Override
    public void logAll(String message) {
      org.apache.log4j.Logger logger=log(null, Level.ALL);
      logger.fatal(message);
    }
    
    
    /**
     * 
     * @param errorClass The class where exception is caught
     * @param message The message to be logged in. The logging will be done for 
     *                every level.
     */

    @Override
    public void logAll(Class errorClass, String message) {
        org.apache.log4j.Logger logger=log(errorClass, Level.ALL);
        logger.fatal(message);
    }

    
    /**
     * 
     * @param errorClass The class where the exception is caught
     * @param message The message to be logged in. The logging will be done for 
     *                every level.
     * @param throwable The exception generated.
     */
    @Override
    public void logAll(Class errorClass, String message, Throwable throwable) {
        org.apache.log4j.Logger logger=log(errorClass, Level.ALL);
        logger.fatal(message,throwable);
    }
    
    private org.apache.log4j.Logger log(Class errorClass,Level level)
    {
        org.apache.log4j.Logger logger=null;
        if(errorClass == null)
            logger= org.apache.log4j.Logger.getLogger(this.getClass().getName());
        else
            logger=org.apache.log4j.Logger.getLogger(errorClass.getName());
            
      if(rollingOn)
      {
          try {
              
              logger.addAppender(getRollingFileAdapter());
          } catch (URISyntaxException e) {
             java.util.logging.Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.SEVERE, 
                     "URL Is Not Valid For Log File\nUnable To Create Daily LOG FILE");
             logger.addAppender(new ConsoleAppender());
          }    
      }
      logger.setLevel(level);
      return logger;
    }
   /**
    * @see org.apache.log4j.DailyRollingFileAppender
    * @return DailyRollingFileAppender. This class is used for creating date wise log.
    * @throws URISyntaxException throws exception if the URI for log directory is not valid 
    */
  private DailyRollingFileAppender getRollingFileAdapter() throws URISyntaxException
  {
      URI logDIRFullName = this.getClass().getResource("/").toURI();
      String normalizePath = Paths.get(logDIRFullName).toString();

      String log_file_full_name = normalizePath + "/" + log_directory_name + "/" + log_file_name;
      PatternLayout layout = new PatternLayout();
      String conversionPattern = getConversionPattern();
      layout.setConversionPattern(conversionPattern);
      DailyRollingFileAppender rollingAppender = new DailyRollingFileAppender();
      rollingAppender.setFile(log_file_full_name);
      rollingAppender.setDatePattern("'.'"+datePattern);
      rollingAppender.setLayout(layout);
      rollingAppender.activateOptions();
      return rollingAppender;
  }

    public String getConversionPattern()
    {
        if(conversionPattern == null)
            return "[%p] %d %c %M - %m%n";
        else
            return conversionPattern;
    }

    public void setConversionPattern(String conversionPattern) {
        this.conversionPattern = conversionPattern;
    }
    
    
  
  
}
