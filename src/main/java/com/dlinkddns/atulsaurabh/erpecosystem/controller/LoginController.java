package com.dlinkddns.atulsaurabh.erpecosystem.controller;

import com.dlinkddns.atulsaurabh.erpecosystem.dto.AuthResult;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.MemberRole;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.Priority;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.CustomFXMLLoader;
import com.dlinkddns.atulsaurabh.erpecosystem.loader.GUIInfo;
import com.dlinkddns.atulsaurabh.erpecosystem.repository.SocietyMemberRepository;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Suyojan
 */
@Controller
public class LoginController 
{
    @Autowired
    private CustomFXMLLoader customFXMLLoader;
    
    @FXML
    private JFXTextField userNameTextBox;
    @FXML
    private JFXPasswordField passwordTextBox;

    @Autowired
    private SocietyMemberRepository societyMemberRepository;
    
    
    @FXML
    public void performLogin(ActionEvent actionEvent)
    {

        AuthResult result = societyMemberRepository.memberLogin(userNameTextBox.getText(),passwordTextBox.getText());
        if (result.isResult()) {
            MemberRole highestPriorityRole = findHighestPriority(result.getRoles());
            Stage stage=null;
            switch(highestPriorityRole.getRolename())
            {
                case "ADMINISTRATOR":
                     stage= customFXMLLoader.createStage(GUIInfo.GUI_ADMIN_DASHBOARD, "window.dashboard.title");
                     break;

            }

            stage.setMaximized(true);
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Login");
            alert.setContentText("Either username or the passphrase is wrong");
            alert.showAndWait();
            userNameTextBox.setText("");
            passwordTextBox.setText("");
        }
    }

    private MemberRole findHighestPriority(Set<MemberRole> roles)
    {
        MemberRole highestMemberRole=new MemberRole();
        highestMemberRole.setPriority(Priority.PRIORITY_LEVEL_TEN);
        Iterator<MemberRole> iterator = roles.iterator();
        while (iterator.hasNext())
        {
            MemberRole memberRole = iterator.next();
            if(memberRole.getPriority() < highestMemberRole.getPriority())
            {
                highestMemberRole=memberRole;
            }
        }
        return highestMemberRole;
    }
    
    public void performReset(ActionEvent actionEvent)
    {
        userNameTextBox.setText("");
        passwordTextBox.setText("");
    }
    
    

    
}
