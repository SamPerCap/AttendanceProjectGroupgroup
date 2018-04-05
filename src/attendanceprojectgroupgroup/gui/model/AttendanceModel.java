/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.model;

import attendanceprojectgroupgroup.be.AClass;
import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.be.Student;
import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.bll.BLLManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Samuel
 */
public class AttendanceModel
{
    BLLManager bllm = new BLLManager();
    
    private ObservableList<StudentAttendance> studentInClassList = FXCollections.observableArrayList();
    private ObservableList<StudentAttendance> studentAttendanceList = FXCollections.observableArrayList();
    private ObservableList<Week> weekList = FXCollections.observableArrayList();
    
    public void getStudentAttendance()
    {
        studentAttendanceList.clear();
        studentAttendanceList.addAll(bllm.getStudentAttendance());
    }
    
    public void getStudentAttendanceByDate(Date date)
    {
        studentAttendanceList.addAll(bllm.getStudentAttendanceByDate(date));
    }
    
    public void clearStudentAttendanceList()
    {
        studentAttendanceList.clear();
    }
    
    public ObservableList<StudentAttendance> loadStudentAttendance()
    {
        return studentAttendanceList;
    }

    
    public List<AClass> getAllClasses() {
        return bllm.getAllClasses();
    }
    
    public boolean studentLogin(String user, String password)
    {
        return bllm.studentLogin(user, password);
    }
    
    public boolean teacherLogin(String user, String password)
    {
        return bllm.teacherLogin(user, password);
    }
    
    public void getWeek()
    {
        weekList.clear();
        weekList.addAll(bllm.getWeek());
    }
    
    public ObservableList<Week> loadWeek()
    {
        return weekList;
    }
    public void editAttendance(StudentAttendance sA) {
        bllm.editAttendance(sA);
    }
    
   
    /**
     * This method gets all Students in class with the selected id.
     *
     * @param selectedId
     * @return
     */
    public void loadStudentsInClass(int selectedId) {
        studentAttendanceList.clear();
        studentAttendanceList.addAll(bllm.getAllStudentsInClass(selectedId));
        
    }

    
    /**
     * This method loads students in a class list.
     *
     * @param cats
     */
    public void loadAllStudentsInClass(List<AClass> clas) {
        studentInClassList.setAll(bllm.getAllStudentsInClass(clas));
    }
               
    /**
     * gets the students in class
     *
     * @return
     */
    public List<StudentAttendance> getStudentsInClass(int selectedId) { 
        return bllm.getAllStudentsInClass(selectedId);
    }
    public void registerChange(){
        bllm.registerChange();
    }
}
