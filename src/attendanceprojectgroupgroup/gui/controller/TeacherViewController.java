/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.AClass;
import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.bll.BLLManager;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.webkit.graphics.GraphicsDecoder;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
    @FXML

    private AttendanceModel model = new AttendanceModel();
    ObservableSet<StudentAttendance> studentsPresence = FXCollections.observableSet();
    private List<Integer> changes;
    BLLManager bll = new BLLManager();
    private TeacherViewController parent2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        changes = new ArrayList<>();
        for (int i = 0; i < bll.getChanges().size(); i++)
        {
            int j = bll.getChanges().get(i);
            changes.add(j);
        }

        columnStudentsName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStudentsAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
        columnStudentDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnStudentPresence.setCellValueFactory(cellData -> cellData.getValue().presenceProperty());
        //Cell factory for toggle buttons:
        buttonsColumn.setCellFactory(param ->
        {
            return new TableCell<StudentAttendance, JFXToggleButton>()
            {
                @Override
                protected void updateItem(JFXToggleButton item, boolean empty)
                {
                    //To change body of generated methods, choose Tools | Templates.
                    super.updateItem(item, empty);
                    if (empty)
                    {
                        setGraphic(null);
                    } else
                    {
                        setGraphic(tglAttendance);
                        String thePresence = getTableView().getItems().get(getIndex()).getPresence();
                        if (thePresence.equals("Here"))
                        {
                            tglAttendance.setSelected(true);
                        } else if (thePresence.equals("Absent"))
                        {
                            tglAttendance.setSelected(false);
                        }
                    }
                }
                //Create toggle button once for cell:
                private final JFXToggleButton tglAttendance = new JFXToggleButton();

                //Anonymous constructor:
                
                {
                    tglAttendance.setSize(5);
                    //Take care of edit the attendance in database
                    tglAttendance.setOnAction((ActionEvent event) ->
                    {
                        StudentAttendance att = ((StudentAttendance) this.getTableRow().getItem());
                        att.setPresence(tglAttendance.getText());
                        model.editAttendance(att);
                    });
                    // keep text "Absent" or "Present" appropriately
                    tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
                }
            };
        }
        );
        buttonsColumn.setResizable(true);
        buttonsColumn.setPrefWidth(100);

        threadLoadsAttendance();

        choiceBoxClass.setItems(FXCollections.observableArrayList(model.getAllClasses()));
        tableStudents.getColumns().add(buttonsColumn);

        checkChoiceBox();
        if (!changes.isEmpty())
        {
            try
            {
                openPopUp();
            } catch (IOException ex)
            {
                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param parent
     */
    public void setParentWindowController(LogInViewController parent)
    {
        this.parent = parent;
    }

    /**
     * Makes a new thread that handles the choicebox changelistener, loads the
     * students from the selected class and adds attendance percentage.
     */
    private void checkChoiceBox()
    {

        Thread t = new Thread(() ->
        {
            choiceBoxClass.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AClass>()
            {
                @Override
                public void changed(ObservableValue<? extends AClass> observable, AClass oldValue, AClass newValue)
                {
                    model.loadStudentsInClass(newValue.getId());
                    attendancePercentage();
                }
            });

        }
        );
        t.start();
    }

    /**
     *
     * @param event Opens the student view
     * @throws IOException
     */
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

    /**
     *
     */
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

    /**
     *
     * @param event
     */
    @FXML
    private void datePicker(ActionEvent event)
    {
    }

    /**
     *
     * @param event
     */
    @FXML
    private void datePickerTo(ActionEvent event)
    {
        model.clearStudentAttendanceList();

        Thread t = new Thread(() ->
        {
            attendanceFromTo();
            attendancePercentage();
        }
        );
        t.start();
    }

    /**
     *
     */
    private void attendanceFromTo()
    {
        int i = 0;
        LocalDate fromDate = dtPicker.getValue();
        LocalDate toDate = dtPickerTo.getValue().plusDays(1);

        while (fromDate.plusDays(i).isBefore(toDate))
        {
            Date date = Date.valueOf(fromDate.plusDays(i));

            model.getStudentAttendanceByDate(date);
            model.loadStudentAttendance();

            i++;
        }
    }

    /**
     *
     */
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
                  
                if (columnStudentsAttendance.getCellData(j) < 80)
                {
//                    columnStudentsAttendance.getCellData(j).setStyle();
                    columnStudentsAttendance.setStyle("-fx-text-fill: red");
                }
            }
        }
    }

    private void openPopUp() throws IOException
    {
        for (int i = 0; i < changes.size(); i++)
        {
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/PopUp.fxml"));

            Parent root = fxLoader.load();

            PopUpController controller = fxLoader.getController();
            controller.setParentWindowController(parent2);
            controller.labelID.setText("" + changes.get(i));
            controller.ID = changes.get(i);

            Scene scene = new Scene(root);
            stage.setTitle("Warning");
            stage.setScene(scene);
            stage.showAndWait();
        }

    }
}
