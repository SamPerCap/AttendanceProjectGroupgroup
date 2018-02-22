/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class StudentViewController implements Initializable {

    @FXML
    protected Label idLabel;
    @FXML
    private TableColumn<Integer, Integer> columnWeek;
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
    private TableView<Integer> weekTableView;

    @FXML
    private Button btnSeeDetailsChart;
    @FXML
    protected Label labelStudentName;
    @FXML
    protected Label labelClass;
    private final List<Integer> yearWeeks = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (int i = 1; i <= 52; i++) {
            yearWeeks.add(i);
            choosingTheWeek();
        }
    }

    @FXML
    private void clickSeeDetailsChart(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        // stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/DetailedChartStudent.fxml"));

        Parent root = fxLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("BarChart");
        stage.setScene(scene);
        stage.showAndWait();
        //doesn't work
        Stage window = (Stage) btnSeeDetailsChart.getScene().getWindow();
        stage.close();

    }
    private LogInViewController parent;

    public void setParentWindowController(LogInViewController parent) {
        this.parent = parent;
    }
    Attendance m = new Attendance();

    private void choosingTheWeek() {
       
    }
}
