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
   
   @Value("${erp.log.fatallogfilename}")
   private String fatal_log_file_name;
   
   @Value("${erp.log.debuflogfilename}")
   private String debug_log_file_name;
   
   @Value("${erp.log.infologfilename}")
   private String info_log_file_name;
   
   @Value("${erp.log.errorlogfilename}")
   private String error_log_file_name;
   
   @Value("${erp.log.warnlogfilename}")
   private String warn_log_file_name;
   
   @Value("${erp.log.alllogfilename}")
   private String all_log_file_name;
   
   
   
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
              String fileName="default.log";
              if(level == Level.ALL)
                  fileName = all_log_file_name;
              else if(level == Level.DEBUG)
                  fileName = debug_log_file_name;
              else if(level == Level.ERROR)
                  fileName = error_log_file_name;
              else if(level == Level.FATAL)
                  fileName = fatal_log_file_name;
              else if(level == Level.INFO)
                  fileName = info_log_file_name;
              else if(level == Level.WARN)
                  fileName = warn_log_file_name;
              
              logger.addAppender(getRollingFileAdapter(fileName));
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
    * @param fileName File name where the log will be recorded.
    */
  private DailyRollingFileAppender getRollingFileAdapter(String fileName) throws URISyntaxException
  {
      URI logDIRFullName = this.getClass().getResource("/").toURI();
      String normalizePath = Paths.get(logDIRFullName).toString();

      String log_file_full_name = normalizePath + "/" + log_directory_name + "/" + fileName;
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

    @Override
    public void logWarning(String message) {
        org.apache.log4j.Logger logger = log(null, Level.WARN);
        logger.warn(message);
    }

    @Override
    public void logWarning(Class warningClass, String message) {
      org.apache.log4j.Logger logger = log(warningClass, Level.WARN);
        logger.warn(message);  
    }

    @Override
    public void logWarning(Class warningClass, String message, Throwable throwable) {
       org.apache.log4j.Logger logger = log(warningClass, Level.WARN);
       logger.warn(message,throwable); 
    }

    @Override
    public void logInfo(String message) {
      org.apache.log4j.Logger logger = log(null, Level.INFO);
       logger.info(message);   
    }

    @Override
    public void logInfo(Class infoClass, String message) {
     org.apache.log4j.Logger logger = log(infoClass, Level.INFO);
     logger.info(message);   
    }

    @Override
    public void logInfo(Class infoClass, String message, Throwable throwable) {
     org.apache.log4j.Logger logger = log(infoClass, Level.INFO);
     logger.info(message,throwable);    
    }

    @Override
    public void logDebug(String message) {
     org.apache.log4j.Logger logger = log(null, Level.DEBUG);
     logger.debug(message);    
    }

    @Override
    public void logDebug(Class debugClass, String message) {
        org.apache.log4j.Logger logger = log(debugClass, Level.DEBUG);
        logger.debug(message);
    }

    @Override
    public void logDebug(Class debugClass, String message, Throwable throwable) {
        org.apache.log4j.Logger logger = log(debugClass, Level.DEBUG);
        logger.debug(message,throwable);
    }

    @Override
    public void logError(String message) {
     org.apache.log4j.Logger logger = log(null, Level.ERROR);
        logger.error(message);   
    }

    @Override
    public void logError(Class errorClass, String message) {
        org.apache.log4j.Logger logger = log(errorClass, Level.ERROR);
        logger.debug(message);
    }

    @Override
    public void logError(Class errorClass, String message, Throwable throwable) {
      org.apache.log4j.Logger logger = log(errorClass, Level.ERROR);
        logger.debug(message,throwable);  
    }
}
