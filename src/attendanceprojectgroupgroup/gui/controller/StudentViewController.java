/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
    private TableColumn<Week, JFXToggleButton> columnMonday;
    @FXML
    private TableColumn<Week, JFXToggleButton> columnTuesday;
    @FXML
    private TableColumn<Week, JFXToggleButton> columnWednesday;
    @FXML
    private TableColumn<Week, JFXToggleButton> columnThursday;
    @FXML
    private TableColumn<Week, JFXToggleButton> columnFriday;
    @FXML
    private TableView<Week> weekTableView;
    @FXML
    protected Label labelStudentName;
    @FXML
    protected Label labelClass;
    private LogInViewController parent;
    private AttendanceModel model = new AttendanceModel();
    private int n;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnWeek.setCellValueFactory(new PropertyValueFactory("weekNumber"));
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

        columnMonday.setCellValueFactory(cell
                -> new ReadOnlyObjectWrapper<>());

        //Cell factory for toggle buttons:
        columnMonday.setCellFactory(param
                -> new TableCell<Week, JFXToggleButton>()
        {
            //It manages the creation and settings 
            @Override
            protected void updateItem(JFXToggleButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setGraphic(null);
                } else
                {
                    fakeData();
                    setGraphic(tglAttendance);
                    if (fakeData() == 1)
                    {
                        tglAttendance.setSelected(true);
                    } else
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
                tglAttendance.setEllipsisString("");
                tglAttendance.setOnAction(((event) ->
                {
                    if (idLabel.getText().equals("1"))
                    {
                        model.registerChange();
                    }
                }));
                // keep text "Absent" or "Present" appropriately
                tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
            }
        }
        );
        columnTuesday.setCellFactory(param
                -> new TableCell<Week, JFXToggleButton>()
        {
            @Override
            protected void updateItem(JFXToggleButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setGraphic(null);
                } else
                {
                    fakeData();
                    setGraphic(tglAttendance);
                    if (fakeData() == 1)
                    {
                        tglAttendance.setSelected(true);
                    } else
                    {
                        tglAttendance.setSelected(false);
                    }
                }
            }
            private final JFXToggleButton tglAttendance = new JFXToggleButton();

            
            {
                tglAttendance.setSize(5);
                tglAttendance.setEllipsisString("");
                tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
            }
        }
        );
        columnWednesday.setCellFactory(param
                -> new TableCell<Week, JFXToggleButton>()
        {
            @Override
            protected void updateItem(JFXToggleButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setGraphic(null);
                } else
                {
                    fakeData();
                    setGraphic(tglAttendance);
                    if (fakeData() == 1)
                    {
                        tglAttendance.setSelected(true);
                    } else
                    {
                        tglAttendance.setSelected(false);
                    }
                }
            }
            private final JFXToggleButton tglAttendance = new JFXToggleButton();

            
            {
                tglAttendance.setSize(5);
                tglAttendance.setEllipsisString("");
                tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
            }
        }
        );
        columnThursday.setCellFactory(param
                -> new TableCell<Week, JFXToggleButton>()
        {
            @Override
            protected void updateItem(JFXToggleButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setGraphic(null);
                } else
                {
                    fakeData();
                    setGraphic(tglAttendance);
                    if (fakeData() == 1)
                    {
                        tglAttendance.setSelected(true);
                    } else
                    {
                        tglAttendance.setSelected(false);
                    }
                }
            }
            private final JFXToggleButton tglAttendance = new JFXToggleButton();

            
            {
                tglAttendance.setSize(5);
                tglAttendance.setEllipsisString("");
                tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
            }
        }
        );
        columnFriday.setCellFactory(param
                -> new TableCell<Week, JFXToggleButton>()
        {
            @Override
            protected void updateItem(JFXToggleButton item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setGraphic(null);
                } else
                {
                    fakeData();
                    setGraphic(tglAttendance);
                    if (fakeData() == 1)
                    {
                        tglAttendance.setSelected(true);
                    } else
                    {
                        tglAttendance.setSelected(false);
                    }
                }
            }
            private final JFXToggleButton tglAttendance = new JFXToggleButton();

            
            {
                tglAttendance.setSize(5);
                tglAttendance.setEllipsisString("");
                tglAttendance.textProperty().bind(Bindings.when(tglAttendance.selectedProperty()).then("Here").otherwise("Absent"));
            }
        }
        );
    }

    /**
     *
     * @return A number that the program translate to button selected or not
     */
    private int fakeData()
    {
        Random rand = new Random();
        n = rand.nextInt(2) + 1;
        return n;
    }

    public void setParentWindowController(LogInViewController parent)
    {
        this.parent = parent;
    }
}
