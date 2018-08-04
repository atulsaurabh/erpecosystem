/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 * @author Atul Saurabh
 * @version 1.0
 */
public class ErpUtilityImpl implements ErpUtility
{
    @Autowired
    private Environment environment;

    @Override
    public String resolvKey(String key) {
        return environment.getProperty(key);
    }
    
    
}
