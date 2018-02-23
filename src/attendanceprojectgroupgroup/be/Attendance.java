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
 * @author Jesper
 */
public class Attendance
{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty studentId = new SimpleIntegerProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty attendance = new SimpleStringProperty();

    public IntegerProperty getId()
    {
        return id;
    }

    public void setId(int value)
    {
        id.set(value);
    }

    public IntegerProperty getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int value)
    {
        id.set(value);
    }

    public StringProperty getDate()
    {
        return date;
    }

    public void setDate(String value)
    {
        date.set(value);
    }

    public StringProperty getAttendance()
    {
        return attendance;
    }

    public void setAttendance(String value)
    {
        attendance.set(value);
    }

    @Override
    public String toString()
    {
        return date.getValue() + attendance.getValue();
    }

}
