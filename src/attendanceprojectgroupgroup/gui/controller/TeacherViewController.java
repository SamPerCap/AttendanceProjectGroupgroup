/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.be.Student;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anni
 */
public class TeacherViewController implements Initializable
{

    private LogInViewController parent;
    @FXML
    public Label labelTeachersName;
    @FXML
    private Button btnAttendanceChart;
    @FXML
    private Button btnAttendanceDayChart;
    @FXML
    private Button btnStudentDetails;
    @FXML
    private JFXToggleButton tglAttendance;
    @FXML
    private TableView<Attendance> tableStudents;
    @FXML
    private TableColumn<Student, String> columnStudentsName;
    @FXML
    private TableColumn<Attendance, String> columnStudentsAttendance;
    @FXML
    private JFXDatePicker dtPicker;
    
    private AttendanceModel model = new AttendanceModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //editingCells();
        columnStudentsName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStudentsAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
        
        model.getAttendance();
        
        tableStudents.setItems(model.loadAttendance());
        
        tglAttendance.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(tglAttendance.isSelected() == true)
                {
                    System.out.println("Hello");
                    tglAttendance.setText("Here");
                }
                else
                {
                    System.out.println("Not hello");
                    tglAttendance.setText("Not here");
                }
            }     
        });
    }

    public void setParentWindowController(LogInViewController parent)
    {
        this.parent = parent;
    }

    @FXML
    private void clickOpenAttendanceChart(ActionEvent event)
    {
    }

    @FXML
    private void clickOpenAttendanceDayChart(ActionEvent event)
    {
    }

    @FXML
    private void clickStudentDetails(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/StudentView.fxml"));

        Parent root = fxLoader.load();

        StudentViewController controller = fxLoader.getController();
        controller.setParentWindowController(parent);

        Scene scene = new Scene(root);
        stage.setTitle("Student");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void toggleAttendance(ActionEvent event)
    {
        System.out.println("Action hello");
    }

    @FXML
    private void datePicker(ActionEvent event)
    {
        
    }
}
