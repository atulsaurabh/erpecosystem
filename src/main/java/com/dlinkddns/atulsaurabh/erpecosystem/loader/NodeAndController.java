/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.loader;

import javafx.scene.Parent;

/**
 *
 * @author Atul Saurabh
 */
public class NodeAndController 
{
   private Parent parent;
   private Object controller;

    public Parent getNode() {
        return parent;
    }

    public void setNode(Parent parent) {
        this.parent = parent;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
   
   
}
