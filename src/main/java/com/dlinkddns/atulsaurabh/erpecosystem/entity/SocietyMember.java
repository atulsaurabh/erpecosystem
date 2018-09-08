
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
   @NotNull
   @NotEmpty
   private String firstname;
   @NotNull
   @NotEmpty
   private String lastname;
   
   private char housetype;
   private int housenumber;
   @Size(min=10,max=10)
   private String mobilenumber;
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   @JoinTable(name = "members_roles",
           joinColumns = @JoinColumn(name = "member"),
           inverseJoinColumns = @JoinColumn(name="role")
   )
   private Set<MemberRole> memberRoles=new HashSet<>();
   @NotEmpty
   @NotNull
   private String username;
   @NotEmpty
   @NotNull
   @Size(max=8,min=4)
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
