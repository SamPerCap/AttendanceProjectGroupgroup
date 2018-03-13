/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    protected Label labelTeachersName;
    @FXML
    private Button btnStudentDetails;
    @FXML
    private JFXToggleButton tglAttendance;

    @FXML
    private TableView<StudentAttendance> tableStudents;
    @FXML
    private TableColumn<StudentAttendance, String> columnStudentsName;
    @FXML
    private TableColumn<StudentAttendance, Float> columnStudentsAttendance;
    @FXML
    private TableColumn<StudentAttendance, String> columnStudentPresence;

    @FXML
    private JFXDatePicker dtPicker;

    private AttendanceModel model = new AttendanceModel();
    @FXML
    private ChoiceBox<?> choiceBoxClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnStudentsName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStudentsAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
        columnStudentPresence.setCellValueFactory(param -> param.getValue().presenceProperty());

        Thread t = new Thread(() ->
        {
            model.getStudentAttendance();

            Platform.runLater(() ->
            {
                tableStudents.setItems(model.loadStudentAttendance());
            });
        }
        );
        t.start();

        //   choiceBoxClass.setItems(FXCollections.observableArrayList(model.getAllClasses()));
// also go to dal and delete or remove outcommenting
        //issue with the above, not sure if it's because you didn't make any classes?
    }

    private String getPresence()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getPresence();

    }

    private String getName()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getName();
    }

    public void setParentWindowController(LogInViewController parent)
    {
        this.parent = parent;
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

        tglAttendance.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if (tglAttendance.isSelected() == true)
            {
                tglAttendance.setText("Present");

            } else
            {
                tglAttendance.setText("Absent");

            }
        });
        changePressence();

    }

    private void changePressence()
    {
        if (tglAttendance.getText() == "Present")
        {
            tableStudents.getItems().stream()
                    .filter(row -> row.getPresence().equals("here"))
                    .findFirst()
                    .ifPresent(row -> row.setPresence("Absent"));
        } else if (tglAttendance.getText() == "Absent")
        {
            tableStudents.getItems().stream()
                    .filter(row -> row.getPresence().equals("Absent"))
                    .findFirst()
                    .ifPresent(row -> row.setPresence("here"));
        }
    }

    @FXML
    private void datePicker(ActionEvent event)
    {
        System.out.println(dtPicker.getValue());
    }
}
