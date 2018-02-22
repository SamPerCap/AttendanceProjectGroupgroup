/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Samuel
 */
public class Week {

    private final IntegerProperty weekNumber = new SimpleIntegerProperty();
    private final StringProperty date = new SimpleStringProperty();
    private int theNumber;

    public IntegerProperty getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int value) {
        weekNumber.set(value);
    }

    public StringProperty getDate() {
        return date;
    }

    public void setDate(String value) {
        date.set(value);
    }

    @Override
    public String toString() {
        return "WeekNumber{" + "weekNumber=" + weekNumber + ", date=" + date + '}';
    }

}
