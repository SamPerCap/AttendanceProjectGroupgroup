/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.be;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jesper
 */
public class StudentAttendance
{
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final FloatProperty attendance = new SimpleFloatProperty();
    private final StringProperty presence = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    
    public int getId()
    {
        return id.get();
    }

    public void setId(int value)
    {
        id.set(value);
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String value)
    {
        name.set(value);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public String getPresence()
    {
        return presence.get();
    }

    public void setPresence(String value)
    {
        presence.set(value);
    }

    public StringProperty presenceProperty()
    {
        return presence;
    }

    public Float getAttendance()
    {
        return attendance.get();
    }

    public void setAttendance(Float value)
    {
        attendance.set(value);
    }

    public FloatProperty attendanceProperty()
    {
        return attendance;
    }
    
    public String getDate()
    {
        return name.get();
    }

    public void setDate(String value)
    {
        name.set(value);
    }

    public StringProperty dateProperty()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name.getValue() + presence.getValue() + attendance.getValue();
    }
}
