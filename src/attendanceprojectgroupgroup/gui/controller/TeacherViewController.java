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
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anni
 */
public class TeacherViewController implements Initializable
{

    @FXML
    private ListView<?> lstStudents;
    @FXML
    private Button btnChart1;
    @FXML
    private Button btnChart2;
    @FXML
    private Button btnChart3;
    @FXML
    private Button btnStudentView;
    private LogInViewController parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

        
    public void setParentWindowController(LogInViewController parent) 
    {
        this.parent = parent;
    }
        
    @FXML
    private void clickOpenChart1(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void clickOpenChart2(ActionEvent event)
    {
    }

    @FXML
    private void clickOpenChart3(ActionEvent event)
    {
    }

    @FXML
    private void clickStudentView(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();

//        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/StudentView.fxml"));

        Parent root = fxLoader.load();

        StudentViewController controller = fxLoader.getController();
        controller.setParentWindowController(parent);

        Scene scene = new Scene(root);
        stage.setTitle("Student");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
