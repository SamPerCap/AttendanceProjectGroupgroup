/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
