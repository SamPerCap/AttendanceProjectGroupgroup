package attendanceprojectgroupgroup.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Week {

    private final IntegerProperty weekNumber = new SimpleIntegerProperty();
    private final StringProperty monday = new SimpleStringProperty();
    private final StringProperty tuesday = new SimpleStringProperty();
    private final StringProperty wednesday = new SimpleStringProperty();
    private final StringProperty thursday = new SimpleStringProperty();
    private final StringProperty friday = new SimpleStringProperty();

    public String getFriday() {
        return friday.get();
    }

    public void setFriday(String value) {
        friday.set(value);
    }

    public StringProperty fridayProperty() {
        return friday;
    }

    public String getThursday() {
        return thursday.get();
    }

    public void setThursday(String value) {
        thursday.set(value);
    }

    public StringProperty thursdayProperty() {
        return thursday;
    }

    public String getWednesday() {
        return wednesday.get();
    }

    public void setWednesday(String value) {
        wednesday.set(value);
    }

    public StringProperty wednesdayProperty() {
        return wednesday;
    }

    public String getTuesday() {
        return tuesday.get();
    }

    public void setTuesday(String value) {
        tuesday.set(value);
    }

    public StringProperty tuesdayProperty() {
        return tuesday;
    }

    public String getMonday() {
        return monday.get();
    }

    public void setMonday(String value) {
        monday.set(value);
    }

    public StringProperty mondayProperty() {
        return monday;
    }

    public int getWeekNumber() {
        return weekNumber.get();
    }

    public void setWeekNumber(int value) {
        weekNumber.set(value);
    }

    public IntegerProperty weekNumberProperty() {
        return weekNumber;
    }
}