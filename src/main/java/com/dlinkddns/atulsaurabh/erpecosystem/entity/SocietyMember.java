/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Atul Saurabh
 */

@Entity
public class SocietyMember implements Serializable
{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int userid;
   private String firstname;
   private String lastname;
   private char housetype;
   private int housenumber;
   private String mobilenumber;
   private boolean administrator;
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   @JoinTable(name = "members_roles",
           joinColumns = @JoinColumn("member"),
           inverseJoinColumns = @JoinColumn("role")
   )
   private Set<MemberRole> memberRoles=new HashSet<>();
   private String username;
   private String passphrase;
   private String accountstatus;
   @Column(length = 400)
   private String publickey;

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }
   
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getHousetype() {
        return housetype;
    }

    public void setHousetype(char housetype) {
        this.housetype = housetype;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Set<MemberRole> getMemberRoles() {
        return memberRoles;
    }

    public void setMemberRoles(Set<MemberRole> memberRoles) {
        this.memberRoles = memberRoles;
    }
      
}
