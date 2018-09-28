/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.code.CodeAndMessage;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.MemberRole;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.Priority;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.RoleInfo;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.erpecosystem.service.SocietyMemberService;
import com.dlinkddns.atulsaurabh.erpecosystem.util.PasswordEncoder;
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
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    @Autowired
    private Validator validator;
    private Parent parent;
    
    
    @FXML
    private Label firstNameError;

    @FXML
    private Label lastNameError;

    @FXML
    private Label userNameError;

    @FXML
    private Label passPhraseError;

    @FXML
    private Label mobileNumberError;

    @FXML
    private Label accountStatusError;

    @FXML
    private Label securityKeyError;

    @FXML
    private Label houseTypeError;

    @FXML
    private Label houseNumberError;

    private boolean keyFileSaved;
    private Key privateKey;
    

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
        while(!keyFileSaved)
        {
            try {
                saveKeyFile();
            }
            catch (IOException io)
            {
                logger.logFatal(this.getClass(), "Unable To Create Key File On Specified Format", io);
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Unable to create key file in the filessytem.Kindly check the permission");
                alert.setHeaderText("Key File Creation Fail");
                alert.setTitle("Key File Required");
                alert.showAndWait();
            }

        }
        SocietyMember societyMember=buildSocietyMember();
        if(validate(societyMember)) {
            Alert alert = null;
            CodeAndMessage codeAndMessage = societyMemberService.createNewSocietyMember(societyMember);
            switch(codeAndMessage.getErpCode()) {
                case ALREADY_EXIST:
                    alert=new Alert(AlertType.ERROR);
                    alert.setContentText(codeAndMessage.getMessage());
                    alert.setHeaderText("Exists...");
                    alert.showAndWait();
                    break;
                case SUCCESSFULLY_CREATED:

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(codeAndMessage.getMessage());
                    alert.setHeaderText("Success");
                    Optional<ButtonType> clicked=alert.showAndWait();
                    if(clicked.get() == ButtonType.OK)
                        clear();
                    break;
                case SERVER_ERROR:
                    alert=new Alert(AlertType.ERROR);
                    alert.setContentText(codeAndMessage.getMessage());
                    alert.setHeaderText("Error...");
                    alert.showAndWait();
                    break;
                case DATABASE_COMM_FAILURE:
                    alert=new Alert(AlertType.ERROR);
                    alert.setContentText(codeAndMessage.getMessage());
                    alert.setHeaderText("Database Comm fail...");
                    alert.showAndWait();
                    break;
                case COMMUNICATION_FAIL:
                    alert=new Alert(AlertType.ERROR);
                    alert.setContentText(codeAndMessage.getMessage());
                    alert.setHeaderText("Rest Comm fail...");
                    alert.showAndWait();
            }

        }
    }

    private boolean validate(SocietyMember societyMember)
    {
        setAllMessageBlank();
        Set<ConstraintViolation<SocietyMember>>  validMember = validator.validate(societyMember);
        if (validMember.isEmpty())
            return true;
        else
        {
            for(ConstraintViolation<SocietyMember> constraintViolation : validMember)
            {
                constraintViolation.getPropertyPath().forEach(path -> {
                    switch(path.getName())
                    {
                        case "firstname":
                            firstNameError.setText(constraintViolation.getMessage());
                            break;
                        case "lastname":
                            lastNameError.setText(constraintViolation.getMessage());
                            break;
                        case "username":
                            userNameError.setText(constraintViolation.getMessage());
                            break;
                        case "passphrase":
                            passPhraseError.setText(constraintViolation.getMessage());
                            break;
                        case "mobilenumber":
                            mobileNumberError.setText(constraintViolation.getMessage());
                            break;
                        case "housenumber":
                            houseNumberError.setText(constraintViolation.getMessage());
                            break;
                        case "housetype":
                            houseTypeError.setText(constraintViolation.getMessage());
                            break;
                        case "accountstatus":
                            accountStatusError.setText(constraintViolation.getMessage());
                            break;
                        case "publickey":
                            securityKeyError.setText(constraintViolation.getMessage());
                            break;
                    }
                });
            }
            return false;
        }
    }

    private void setAllMessageBlank()
    {
        firstNameError.setText("");
        lastNameError.setText("");
        userNameError.setText("");
        passPhraseError.setText("");
        mobileNumberError.setText("");
        accountStatusError.setText("");
        houseTypeError.setText("");
        houseNumberError.setText("");
        securityKeyError.setText("");
    }

    @FXML
    public void createAndSaveKey(ActionEvent actionEvent)
    {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            
            KeyPair keyPair=keyPairGenerator.genKeyPair();
            privateKey = keyPair.getPrivate();
            Key publicKey  = keyPair.getPublic();
            Base64.Encoder encoder = Base64.getEncoder();
            securityKey.setText(encoder.encodeToString(publicKey.getEncoded()));
            saveKeyFile();

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


    private void saveKeyFile() throws IOException
    {
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
            alert.setContentText("Key file generated successfully.\n "
                    + "Kindly circulate the file at "+keyFilePath.getAbsolutePath()+" \nfor secure communication");
            alert.setHeaderText("Key File Created");
            alert.setTitle("Key File");
            alert.showAndWait();
            keyFileSaved=true;
        }
        else
        {
            keyFileSaved=false;
        }
    }

    private SocietyMember buildSocietyMember() {
        
        SocietyMember member=new SocietyMember();
        member.setFirstname(firstName.getText());
        member.setLastname(lastName.getText());
        member.setHousetype(houseType.getSelectionModel().getSelectedItem());
        Integer hNumber=houseNumber.getSelectionModel().getSelectedItem();
        if(hNumber == null)
        {
            member.setHousenumber(0);
        }
        else {
            member.setHousenumber(hNumber);
        }
        member.setUsername(userName.getText());
        member.setPassphrase(PasswordEncoder.encode(passphrase.getText()));
        member.setPublickey(securityKey.getText());
        if(administrator.isSelected())
            member.getMemberRoles().add(new MemberRole(
                    RoleInfo.ADMINISTRATOR,Priority.PRIORITY_LEVEL_ONE));
        if(secretary.isSelected())
            member.getMemberRoles().add(new MemberRole(
                    RoleInfo.SECRETARY,Priority.PRIORITY_LEVEL_TWO));
        else if(coord.isSelected())
            member.getMemberRoles().add(new MemberRole(
                    RoleInfo.COORDINATOR,Priority.PRIORITY_LEVEL_THREE));
        else if(this.member.isSelected())
            member.getMemberRoles().add(new MemberRole(
                    RoleInfo.SOCIETY_MEMBER,Priority.PRIORITY_LEVEL_FOUR));
        member.setMobilenumber(mobileNumber.getText());
        member.setAccountstatus(accountStatus.getSelectionModel().getSelectedItem());
        member.setPublickey(securityKey.getText());
        return member;
    }


    private void clear()
    {
        keyFileSaved=true;
        privateKey=null;
        firstName.setText("");
        lastName.setText("");
        userName.setText("");
        passphrase.setText("");
        securityKey.setText("");
        mobileNumber.setText("");
        houseNumber.getSelectionModel().clearSelection();
        houseType.getSelectionModel().clearSelection();
        accountStatus.getSelectionModel().clearSelection();
        administrator.setSelected(false);
        member.setSelected(true);
        coord.setSelected(false);
        secretary.setSelected(false);
    }
}
