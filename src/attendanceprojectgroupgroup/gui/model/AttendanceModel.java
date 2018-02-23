/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.model;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.be.StudentAttendance;
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
    
    private ObservableList<StudentAttendance> studentAttendanceList = FXCollections.observableArrayList();
    
    public void getStudentAttendance()
    {
        studentAttendanceList.clear();
        studentAttendanceList.addAll(bll.getStudentAttendance());
    }
    
    public ObservableList<StudentAttendance> loadStudentAttendance()
    {
        return studentAttendanceList;
    }
}
