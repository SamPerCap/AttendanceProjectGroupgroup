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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class DetailedChartStudentController {

    @FXML
    private BarChart<String, Integer> weekDetailChart;
    @FXML
    private CategoryAxis xBar;

    @FXML
    private NumberAxis yAxis;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO

        settingTheData();
    }

    private void settingTheData() {
        String[] weeks = DateFormatSymbols.getInstance(Locale.ENGLISH).getWeekdays();
        Random r = new Random();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 2; i < 7; i++) {
            series.getData().add(new XYChart.Data<>(weeks[i], r.nextInt(100)));
        }
        weekDetailChart.getData().add(series);

    }

    private LogInViewController parent;

    public void setParentWindowController(LogInViewController parent) {
        this.parent = parent;
    }

}
