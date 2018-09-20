
package com.dlinkddns.atulsaurabh.erpecosystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

/**
 *
 * @author Atul Saurabh
 */

public class SocietyMember implements Serializable
{
   

   private int userid;
   @NotNull(message="First name can not be null")
   @NotEmpty(message="First name can not be empty")
   @NotBlank(message="First name can not be blank")
   private String firstname;
   @NotNull(message="Last name can not be null")
   @NotEmpty(message="Last name can not be empty")
   @NotBlank(message="Last name can not be blank")
   private String lastname;
   @Pattern(regexp = "^[AB]{1}$",message = "Select house type")
   @NotNull(message = "Select house type")
   private String housetype;
   @Positive(message = "Select house number ")
   private int housenumber;
   @Size(min=10,max=10,message="Mobile number can not less than 10 digits")
   private String mobilenumber;

   private Set<MemberRole> memberRoles=new HashSet<>();
   @NotNull(message="User name can not be null")
   @NotEmpty(message="User name can not be empty")
   @NotBlank(message="User name can not be blank")
   private String username;
   @NotNull(message="Password can not be null")
   @NotEmpty(message="Password can not be empty")
   @NotBlank(message="Password can not be blank")
   @Size(min = 4,max = 8,message = "Password length can not be less than 4 and more than 8")
   private String passphrase;
   @NotNull(message = "Select account status")
   private String accountstatus;

   @NotEmpty(message = "Generate a key for the user")
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

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
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
