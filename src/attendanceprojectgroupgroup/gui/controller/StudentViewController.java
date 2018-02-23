/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class StudentViewController implements Initializable
{

    @FXML
    protected Label idLabel;

    @FXML
    private TableColumn<Week, Integer> columnWeek;
    @FXML
    private TableColumn<Week, String> columnMonday;
    @FXML
    private TableColumn<Week, String> columnTuesday;
    @FXML
    private TableColumn<Week, String> columnWednesday;
    @FXML
    private TableColumn<Week, String> columnThursday;
    @FXML
    private TableColumn<Week, String> columnFriday;
    @FXML
    private TableView<Week> weekTableView;

    @FXML
    protected Label labelStudentName;
    @FXML
    protected Label labelClass;
    
    private final List<Integer> yearWeeks = new ArrayList<>();
    
    private AttendanceModel model = new AttendanceModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnWeek.setCellValueFactory(new PropertyValueFactory("weekNumber"));
        columnMonday.setCellValueFactory(new PropertyValueFactory("monday"));
        columnTuesday.setCellValueFactory(new PropertyValueFactory("tuesday"));
        columnWednesday.setCellValueFactory(new PropertyValueFactory("wednesday"));
        columnThursday.setCellValueFactory(new PropertyValueFactory("thursday"));
        columnFriday.setCellValueFactory(new PropertyValueFactory("friday"));

        
        Thread t = new Thread(() ->
        {
            model.getWeek();

            Platform.runLater(() ->
            {
                weekTableView.setItems(model.loadWeek());
            });
        }
        );
        t.start();
    }
    
    public void setParentWindowController(LogInViewController parent)
    {
        this.parent = parent;
    }

    private LogInViewController parent;

    private void choosingTheWeek()
    {

    }
}
