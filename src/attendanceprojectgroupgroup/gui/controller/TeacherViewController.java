/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.AClass;
import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter.Change;
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
    private TableColumn<StudentAttendance, StudentAttendance> buttonsColumn;
    @FXML
    private ChoiceBox<AClass> choiceBoxClass;

    private AttendanceModel model = new AttendanceModel();
    StudentAttendance sModel = new StudentAttendance();

    @FXML
    private JFXDatePicker dtPicker;
    @FXML
    private JFXDatePicker dtPickerTo;

    private int studentID;
    private float attendanceInfo;

    @FXML
    ObservableSet<StudentAttendance> studentsPresence = FXCollections.observableSet();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        columnStudentsName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStudentsAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
    }

    private String getPresence()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getPresence();
    }

    private String getName()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getName();
    }

    private int getStudentID()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getId();
    }

    private Float getAttendance()
    {
        return tableStudents.getSelectionModel().getSelectedItem().getAttendance();
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

    private void toggleAttendance(ActionEvent event)
    {

        tglAttendance.selectedProperty().addListener(new ChangeListener<Boolean>()
        {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {

                if (tglAttendance.isSelected() == true)
                {
                    tglAttendance.setText("Present");

                } else
                {
                    tglAttendance.setText("Absent");

                }
            }
        });
        changePressence();

    }

    private void changePressence()
    {

        if (tglAttendance.getText() == "Present")
        {
            setAbsent();

            studentID = getStudentID();
            attendanceInfo = getAttendance();
            System.out.println(studentID + " " + attendanceInfo);

        } else if (tglAttendance.getText() == "Absent")
        {
            setHere();

            studentID = getStudentID();
            attendanceInfo = getAttendance();
            System.out.println(studentID + " " + attendanceInfo);

        }
    }

    private void setAbsent()
    {
        tableStudents.getItems().stream()
                .filter(row -> row.getPresence().equals("Here"))
                .findFirst()
                .ifPresent(row -> row.setPresence("Absent"));
    }

    private void setHere()
    {
        tableStudents.getItems().stream()
                .filter(row -> row.getPresence().equals("Absent"))
                .findFirst()
                .ifPresent(row -> row.setPresence("Here"));
    }

    @FXML
    private void datePicker(ActionEvent event)
    {
        //System.out.println(dtPicker.getValue());
    }

    @FXML
    private void datePickerTo(ActionEvent event)
    {
        //System.out.println(dtPickerTo.getValue());
        //attendanceFromTo();
        attendancePercentage();
    }

    private void attendanceFromTo()
    {
        int i = 0;
        LocalDate fromDate = dtPicker.getValue();
        LocalDate toDate = dtPickerTo.getValue().plusDays(1);

        while (fromDate.plusDays(i).isBefore(toDate))
        {
            System.out.println(fromDate.plusDays(i++));
        }
    }

    private void attendancePercentage()
    {
        for (int i = 0; i < model.loadStudentAttendance().size(); i++)
        {
            int count = 0;
            int absense = 0;

            for (int j = 0; j < model.loadStudentAttendance().size(); j++)
            {
                tableStudents.getSelectionModel().select(j);
                int studentId = tableStudents.getSelectionModel().getSelectedItem().getId();

                if (studentId == i)
                {
                    String studentPresence = tableStudents.getSelectionModel().getSelectedItem().getPresence();

                    if (studentPresence.equals("Absent"))
                    {
                        absense++;
                    }

                    count++;
                }
            }

            float absensePercentage = 100 - ((absense * 1f / count) * 100);

            for (int j = 0; j < model.loadStudentAttendance().size(); j++)
            {
                tableStudents.getSelectionModel().select(j);
                int studentId = tableStudents.getSelectionModel().getSelectedItem().getId();

                if (studentId == i)
                {
                    tableStudents.getSelectionModel().select(j);
                    tableStudents.getSelectionModel().getSelectedItem().setAttendance(absensePercentage);
                }
            }
        }
    }
}
