/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.configuration;

import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GraphicsSupplier;
import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtility;
import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtilityImpl;


import java.io.InputStream;
import java.util.Properties;

import javax.validation.Validator;

import net.dlinkddns.atulsaurabh.hasselfreelogger.api.Logger;
import net.dlinkddns.atulsaurabh.hasselfreelogger.impl.HasselFreeLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;


/**
 *
 * @author Atul Saurabh
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackages = {"com.dlinkddns.atulsaurabh.erpecosystem.loader"})
@PropertySources(
        {
            @PropertySource("classpath:window.properties"),
            @PropertySource("classpath:restendpoints.properties")
        }
)
public class BaseConfiguration 
{
   
    @Bean
    @Qualifier("basiclogger")
    public Logger logger()
    {
        return new HasselFreeLogger();
    }
    
    @Bean
    @Primary
    public Logger rollingLogger()
    {  
        Properties properties=new Properties();
        try {
            InputStream inputStream = new ClassPathResource("application.properties").getInputStream();
            properties.load(inputStream);
        } catch (Exception e) {
        }  
      HasselFreeLogger logger=new HasselFreeLogger(properties);
      return logger;
      
    }
     
     @Bean
     public ErpUtility erpUtility()
     {
         return new ErpUtilityImpl();
     }
     
    @Bean
    public CustomFXMLLoader customFXMLLoader()
    {
        return new CustomFXMLLoader();
    }
    
    @Bean
    public Validator validator()
    {
    	return new LocalValidatorFactoryBean();
    	
    }
    
    @Bean
    public GraphicsSupplier graphicsSupplier()
    {
    	return new GraphicsSupplier();
    }
    
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor()
    {
    	MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    	postProcessor.setValidator(validator());
    	return postProcessor;
    }
}