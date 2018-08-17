/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.custom;

import com.dlinkddns.atulsaurabh.erpecosystem.plugin.PlugEvent;
import com.dlinkddns.atulsaurabh.erpecosystem.plugin.PlugEventListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 *
 * @author Suyojan
 */
public class ErpMenuBar extends MenuBar{
    private PlugEventListener plugEventListener;
    public void setPlugEventListener(PlugEventListener plugEventListener)
    {
        this.plugEventListener = plugEventListener;
    }
    
    public void removePlugEventListener()
    {
        this.plugEventListener = null;
    }
    
    public void processPlugIn(Menu menu)
    {
        PlugEvent plugEvent=new PlugEvent(this, menu);
        plugEventListener.handle(plugEvent);
    }
}
