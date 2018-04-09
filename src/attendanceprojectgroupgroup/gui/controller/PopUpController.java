/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.bll.BLLManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PopUpController implements Initializable
{

    @FXML
    public Label labelID;
    @FXML
    private Button btnReverse;
    @FXML
    private Button btnAccept;
    private TeacherViewController parent;
    BLLManager bll = new BLLManager();
    int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void clickBtnReverse(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Attendance have been modified successfully");
        alert.showAndWait();
        bll.cancelChange(ID);
        Stage stage = (Stage) btnReverse.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void clickBtnAccept(ActionEvent event)
    {
        bll.cancelChange(ID);
        Stage stage = (Stage) btnAccept.getScene().getWindow();
        stage.close();
    }

    public void setParentWindowController(TeacherViewController parent)
    {
        this.parent = parent;
    }

}
