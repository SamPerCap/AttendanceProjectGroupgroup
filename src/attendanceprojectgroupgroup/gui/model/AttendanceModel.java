/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.model;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.bll.BLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Samuel
 */
public class AttendanceModel
{
    BLLManager bll = new BLLManager();
    
    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
    
    public void getAttendance()
    {
        attendanceList.clear();
        attendanceList.addAll(bll.getAttendance());
    }
    
    public ObservableList<Attendance> loadAttendance()
    {
        return attendanceList;
    }
}
