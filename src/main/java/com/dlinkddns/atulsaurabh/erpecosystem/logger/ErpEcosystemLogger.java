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
 * <h3>Dependencies</h3>
 * This implementation is depending upon <a href="https://logging.apache.org/log4j/">Log4j</a>. 
 * To use this class in <a href="https://maven.apache.org/">Maven</a> project following
 * dependency need to added in the pom file.
 * <pre>
 * {@code 
 *        <dependency>
 *        <groupId>log4j</groupId>
 *        <artifactId>log4j</artifactId>
 *        <version>1.2.17</version>
 *        </dependency>
 * }
 * </pre>
 * Otherwise Log4j dependencies need to be satisfied manually.
 * <h3>Purposes</h3>
 * <p>
 * Purpose of this class is to provide a hassle free logging technique. 
 * The implementation supports rolling based logging as well as non rolling
 * based logging mechanism. The rolling option can be activated by setting 
 * {@link #rollingOn} field.
 * 
 * <p>
 * Using this class following level of log can be recorded
 * <pre>
 * {@link org.apache.log4j.Level#ALL}, {@link org.apache.log4j.Level#DEBUG},
 * {@link org.apache.log4j.Level#ERROR}, {@link org.apache.log4j.Level#FATAL},
 * {@link org.apache.log4j.Level#INFO} and {@link org.apache.log4j.Level#WARN}
 * </pre>
 * 
 *<h3>Configuration</h3>
 * <p>
 * On the basis of above mentioned {@link org.apache.log4j.Level} and if the rolling 
 * is active then different type of log is recorded in different files. These files 
 * can be configured by changing inside <b>application.properties</b> file.
 * The following keys are used to configure the log files:
 * <ul>
 *     <li> erp.log.alllogfilename : To log {@link org.apache.log4j.Level#ALL}
 *     <li> erp.log.debuflogfilename : To log {@link org.apache.log4j.Level#DEBUG} 
 *     <li> erp.log.errorlogfilename : To log {@link org.apache.log4j.Level#ERROR}
 *     <li> erp.log.fatallogfilename : To log {@link org.apache.log4j.Level#FATAL}
 *     <li> erp.log.infologfilename : To log {@link org.apache.log4j.Level#INFO}
 *     <li> erp.log.warnlogfilename : To log {@link org.apache.log4j.Level#WARN}
 * </ul>
 * Along with the files, the director for log repository can also be configured
 * by setting the key <b><i>erp.log.directory</i></b> in application.properties file.
 * <h3>Features</h3>
 * <p>
 * The rolling based logging mechanism provides a way to log the message on the 
 * basis of date and time. The date and time option is configurable.
 * The format can be decided using {@link #datePattern}. 
 * <p>
 *The format of content is also configurable. This format can be modified using
 * {@link #conversionPattern}
 * 
 * @see #setRollingOn(boolean)
 * @see #setDatePattern(java.lang.String) 
 * @see #setConversionPattern(java.lang.String) 
 * 
 *@version 1.0
 *@author Atul Saurabh
 *@since 1.0  
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
     * Logs the fatal level. 
     * @param message The fatal level message to be logged in
     * @since 1.0 
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
    
    
    /*
        I believe that all kind of log method requires level to decide 
        what kind of file need to be generated and what format to use while 
        recording the log. So this decision can be made centrally. That is why I
        designed this central method to produce a logger object with the decision of
        a) The level of the log
        b) which class is generating the log
        
        That is why this method requies two parameters
        a) errorClass : where the exception is generated
        b) level of the log.
    */
    private org.apache.log4j.Logger log(Class errorClass,Level level)
    {
        /*
            If the user of the system do not want to supply error generating class
            then by default the current class will be considered to be the class which is 
             generating the eeror.
        */
        org.apache.log4j.Logger logger=null;
        if(errorClass == null)
            logger= org.apache.log4j.Logger.getLogger(this.getClass().getName());
        else
            /*
               
            */
            logger=org.apache.log4j.Logger.getLogger(errorClass.getName());
         /*
            If the user want to record the log into respective file then the rollingOn
            should be true.
        */   
      if(rollingOn)
      {
          try {
              /*
                 I want to record different level of log into different files.
                  So what file wiil be used will depened upon the level.
              */
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

    @Override
    public String getConversionPattern()
    {
        if(conversionPattern == null)
            return "[%p] %d %c %M - %m%n";
        else
            return conversionPattern;
    }

    @Override
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
