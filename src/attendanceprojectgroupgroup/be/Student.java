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
public class Student
{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty attendingClass = new SimpleStringProperty();

    public IntegerProperty getId()
    {
        return id;
    }

    public void setId(int value)
    {
        id.set(value);
    }

    public StringProperty getName()
    {
        return name;
    }

    public void setName(String value)
    {
        name.set(value);
    }

    public StringProperty getAttendingClass()
    {
        return attendingClass;
    }

    public void setAttendingClass(String value)
    {
        attendingClass.set(value);
    }
    
    @Override
    public String toString()
    {
        return name.getValue();
    }
}
