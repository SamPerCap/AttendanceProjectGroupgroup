/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class StudentViewController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private TableColumn<?, ?> columnWeek;
    @FXML
    private TableColumn<?, ?> columnMonday;
    @FXML
    private TableColumn<?, ?> columnTuesday;
    @FXML
    private TableColumn<?, ?> columnWednesday;
    @FXML
    private TableColumn<?, ?> columnThursday;
    @FXML
    private TableColumn<?, ?> columnFriday;
    @FXML
    private TableColumn<?, ?> columnWeekTotal;
    @FXML
    private TableView<?> weekTableView;
    @FXML
    private Button btnSeeDetailsChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickSeeDetailsChart(ActionEvent event) {
        
        
    }
    
}
