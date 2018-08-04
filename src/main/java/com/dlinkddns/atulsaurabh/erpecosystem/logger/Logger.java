/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.logger;

/**
 *
 * @author Atul Saurabh
 * @version 1.0
 */
public interface Logger 
{
   public void logFatal(String message);
   public void logFatal(Class errorClass,String message);
   public void logFatal(Class errorClass,String message,Throwable th);
   public void logAll(String message);
   public void logAll(Class errorClass,String message);
   public void logAll(Class errorClass,String message,Throwable th);
   public void setRollingOn(boolean rollingon);
   public boolean isRollingOn();
   public void setDatePattern(String pattern);
   public String getDatePattern();
   public void setConversionPattern(String pattern);
   public String getConversionPattern();
}
