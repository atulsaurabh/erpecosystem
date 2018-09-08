
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
   @NotNull(message="First name can not be null")
   @NotEmpty(message="First name can not be empty")
   @NotBlank(message="First name can not be blank")
   private String firstname;
   @NotNull(message="Last name can not be null")
   @NotEmpty(message="Last name can not be empty")
   @NotBlank(message="Last name can not be blank")
   private String lastname;
   
   private char housetype;
   private int housenumber;
   @Size(min=10,max=10,message="Mobile number can not less than 10 digits")
   private String mobilenumber;
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   @JoinTable(name = "members_roles",
           joinColumns = @JoinColumn(name = "member"),
           inverseJoinColumns = @JoinColumn(name="role")
   )
   private Set<MemberRole> memberRoles=new HashSet<>();
   @NotNull(message="User name can not be null")
   @NotEmpty(message="User name can not be empty")
   @NotBlank(message="User name can not be blank")
   private String username;
   @NotNull(message="Password can not be null")
   @NotEmpty(message="Password can not be empty")
   @NotBlank(message="Password can not be blank")
   @Min(value=4,message="Password length can not be less than 4")
   @Max(value=8,message="password length can not be less than 8")
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
