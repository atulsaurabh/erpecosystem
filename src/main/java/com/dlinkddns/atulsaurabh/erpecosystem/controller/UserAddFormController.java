/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.entity.MemberRole;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.RoleInfo;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.erpecosystem.service.SocietyMemberService;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import net.dlinkddns.atulsaurabh.hasselfreelogger.api.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Atul Saurabh
 */

@Controller
@Scope("prototype")
public class UserAddFormController {

     @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXComboBox<String> houseType;

    @FXML
    private JFXComboBox<Integer> houseNumber;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField passphrase;

    @FXML
    private JFXCheckBox administrator;

    @FXML
    private JFXTextArea securityKey;

    @FXML
    private JFXRadioButton secretary;

    @FXML
    private ToggleGroup ROLES;

    @FXML
    private JFXRadioButton coord;

    @FXML
    private JFXRadioButton member;

    @FXML
    private JFXComboBox<String> accountStatus;

    @FXML
    private JFXTextField mobileNumber;

    @Autowired
    private Logger logger;
    @Autowired
    private SocietyMemberService societyMemberService;
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    @FXML
    public void initialize()
    {
        ObservableList<String> houseTypes = FXCollections.observableArrayList("A","B");
        houseType.setItems(houseTypes);
        ObservableList<Integer> houseNumbers=FXCollections.observableArrayList();
        for(int i=1;i<210;i++)
        {
            houseNumbers.add(i);
        }
        houseNumber.setItems(houseNumbers);
        ObservableList<String> accountStatuses = FXCollections.observableArrayList("ACTIVE","INACTIVE");
        accountStatus.setItems(accountStatuses);
    }
    
    
    
    @FXML
    public void createUser(ActionEvent actionEvent)
    {
        SocietyMember societyMember=buildSocietyMember();
        Alert alert=null;
        if(societyMemberService.createNewSocietyMember(societyMember))
        {
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Society Member Created Successfully");
            alert.setHeaderText("Success");
            alert.showAndWait();
        }
        else
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Society Member Created Successfully");
            alert.setHeaderText("Success");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void createAndSaveKey(ActionEvent actionEvent)
    {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            
            KeyPair keyPair=keyPairGenerator.genKeyPair();
            Key privateKey = keyPair.getPrivate();
            Key publicKey  = keyPair.getPublic();
            Base64.Encoder encoder = Base64.getEncoder();
            securityKey.setText(encoder.encodeToString(publicKey.getEncoded()));
            System.out.println("Length="+encoder.encodeToString(publicKey.getEncoded()).length());
            FileChooser fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Key File","*.key"));
            fileChooser.setTitle("Save The Key");
            String initialFileName=houseType.getSelectionModel().getSelectedItem()+
                                   houseNumber.getSelectionModel().getSelectedItem();
            if (initialFileName != null && !initialFileName.trim().equals(""))
                fileChooser.setInitialFileName(initialFileName + ".key");
            else
                fileChooser.setInitialFileName("default.key"); 
            File keyFilePath=fileChooser.showSaveDialog(parent.getScene().getWindow());
            if(keyFilePath != null)
            {
                FileOutputStream fos = new FileOutputStream(keyFilePath);
                fos.write(privateKey.getEncoded());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Key file generated successfully. "
                        + "Kindly circulate the file at "+keyFilePath.getAbsolutePath()+" for secure communication");
                alert.setHeaderText("Key File Created");
                alert.setTitle("Key File");
                alert.showAndWait();
            }
        } catch (NoSuchAlgorithmException e) {
            logger.logError(this.getClass(), "RSA Algorithm Implementation Not Available", e);
        }
        catch(IOException ioe)
        {
            
            logger.logFatal(this.getClass(), "Unable To Create Key File On Specified Format", ioe);
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Unable to create key file in the filessytem.Kindly check the permission");
            alert.setHeaderText("Key File Creation Fail");
            alert.setTitle("Key File Required");
            alert.showAndWait();
        }
        
    }

    private SocietyMember buildSocietyMember() {
        
        SocietyMember member=new SocietyMember();
        member.setFirstname(firstName.getText());
        member.setLastname(lastName.getText());
        member.setHousetype(houseType.getSelectionModel().getSelectedItem().charAt(0));
        member.setHousenumber(houseNumber.getSelectionModel().getSelectedItem());
        member.setUsername(userName.getText());
        member.setPassphrase(passphrase.getText());
        member.setPublickey(securityKey.getText());
        if(administrator.isSelected())
            member.getMemberRoles().add(new MemberRole(RoleInfo.ADMINISTRATOR));
        if(secretary.isSelected())
            member.getMemberRoles().add(new MemberRole(RoleInfo.SECRETARY));
        else if(coord.isSelected())
            member.getMemberRoles().add(new MemberRole(RoleInfo.COORDINATOR));
        else if(this.member.isSelected())
            member.getMemberRoles().add(new MemberRole(RoleInfo.SOCIETY_MEMBER));
        member.setMobilenumber(mobileNumber.getText());
        return member;
    }
}
