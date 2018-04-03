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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableColumn<StudentAttendance, JFXToggleButton> buttonsColumn = new TableColumn<>("Presence");

    @FXML
    private TableColumn<StudentAttendance, Date> columnStudentDate;
    @FXML
    private ChoiceBox<AClass> choiceBoxClass;

    @FXML
    private JFXDatePicker dtPicker;
    @FXML
    private JFXDatePicker dtPickerTo;

    private AttendanceModel model = new AttendanceModel();
    private StudentAttendance sModel = new StudentAttendance();

    ObservableSet<StudentAttendance> studentsPresence = FXCollections.observableSet();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnStudentsName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStudentsAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
        columnStudentDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnStudentPresence.setCellValueFactory(cellData -> cellData.getValue().presenceProperty());

        // just use whole row (studentsPresence) as data for cells in this column:
        //buttonsColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>());
        // cell factory for toggle buttons:
        buttonsColumn.setCellFactory(param ->
        {
            return new TableCell<StudentAttendance, JFXToggleButton>()
            {
                @Override
                protected void updateItem(JFXToggleButton item, boolean empty)
                {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    if (empty)
                    {
                        setGraphic(null);
                    } else
                    {
                        setGraphic(tglAttendance);
                        String thePresence = getTableView().getItems().get(getIndex()).getPresence();
 //                       tglAttendance.setText(thePresence);
                        if (thePresence.equals("Here"))
                        {
                            tglAttendance.setSelected(true);
                        }
                        else if (thePresence.equals("Absent"))
                        {
                            tglAttendance.setSelected(false);
                        }
                    }
                }
                // create toggle button once for cell:
                private final JFXToggleButton tglAttendance = new JFXToggleButton();
                //anonymous constructor:
                {
                    tglAttendance.setSize(5);

                    // keep text "Absent" or "Present" appropriately
                    tglAttendance.selectedProperty().addListener((obs, wasSelected, isNowSelected) ->
                    {
                        tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
                    });
                    tglAttendance.setOnAction((ActionEvent event) ->
                    {
                        StudentAttendance att = ((StudentAttendance) this.getTableRow().getItem());
                        if (!att.equals(tglAttendance.getText()))
                        {
                            att.setPresence(tglAttendance.getText());
                            model.editAttendance(att);
                            System.out.println(att);
                            System.out.println(tglAttendance.getText());
                        }
                    });
                }
            };
        }
        );
        buttonsColumn.setResizable(true);
        buttonsColumn.setPrefWidth(100);

        threadLoadsAttendance();

        choiceBoxClass.setItems(FXCollections.observableArrayList(model.getAllClasses()));
        tableStudents.getColumns().add(buttonsColumn);

      //  tableStudents.setItems(model.getStudentsInClassList());
        
        choiceBoxClass.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {  
                checkChoiceBox();
            }    
        });
        
    }
        
    private void checkChoiceBox()
    {
 
        AClass clas = choiceBoxClass.getSelectionModel().getSelectedItem();
        if (clas == null)
        {
            return ;
        }

        System.out.println(clas);
        model.loadStudentsInClass(clas.getId());
      

   //     tableStudents.setItems(model.getStudentsInClassList());


    }

    private StudentAttendance getPresence()
    {
        return tableStudents.getSelectionModel().getSelectedItem();
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

    private void threadLoadsAttendance()
    {
        Thread thread = new Thread(() ->
        {
            model.getStudentAttendance();

            Platform.runLater(() ->
            {
                tableStudents.setItems(model.loadStudentAttendance());

            });
        }
        );
        thread.start();
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

        model.clearStudentAttendanceList();

        Thread t = new Thread(() ->
        {
            attendanceFromTo();
            attendancePercentage();
        }
        );
        t.start();
    }

    private void attendanceFromTo()
    {
        int i = 0;
        LocalDate fromDate = dtPicker.getValue();
        LocalDate toDate = dtPickerTo.getValue().plusDays(1);

        while (fromDate.plusDays(i).isBefore(toDate))
        {

            System.out.println(fromDate.plusDays(i));

            Date date = Date.valueOf(fromDate.plusDays(i));

            model.getStudentAttendanceByDate(date);
            model.loadStudentAttendance();

            i++;
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

            float absensePercentage = 100 - ((absense * 1f / count * 1f) * 100);

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
