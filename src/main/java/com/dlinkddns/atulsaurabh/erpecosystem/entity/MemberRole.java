/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.entity;

import java.io.Serializable;


/**
 *
 * @author Atul Saurabh
 */


public class MemberRole implements Serializable
{

    private int roleid;
    private String rolename;

    public MemberRole() {
    }

    public MemberRole(String rolename) {
        this.rolename = rolename;
    }
    
    

    
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    
}
