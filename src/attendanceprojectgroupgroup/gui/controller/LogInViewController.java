/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class LogInViewController implements Initializable {
    
    
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Button btnLogIn;
    
    private void logInButton(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickLogIn(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();

       // stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/TeacherView.fxml"));

        Parent root = fxLoader.load();

        TeacherViewController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        stage.setTitle("Teacher");
        stage.setScene(scene);
        stage.showAndWait();
        //doesn't work
        Stage window = (Stage) btnLogIn.getScene().getWindow();
        stage.close();
    }
    
 
}
