/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class DetailedChartStudentController implements Initializable {

    @FXML
    private BarChart<String, Integer> weekDetailChart;
    @FXML
    private CategoryAxis xBar;
    
    private ObservableList<String> weekDays = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] weeks = DateFormatSymbols.getInstance(Locale.ENGLISH).getWeekdays();
        weekDays.addAll(Arrays.asList(weeks));
        xBar.setCategories(weekDays);
    }    
    
}
