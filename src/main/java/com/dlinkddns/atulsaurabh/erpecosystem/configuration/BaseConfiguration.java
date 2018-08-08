/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.configuration;

import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtility;
import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtilityImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Atul Saurabh
 * @version 1.0
 */

@Configuration
public class BaseConfiguration 
{
    
     
     
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
}
