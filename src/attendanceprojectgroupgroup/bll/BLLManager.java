/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.bll;

import attendanceprojectgroupgroup.be.AClass;
import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.dal.DALManager;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class BLLManager
{

    DALManager dalm = new DALManager();

    public List<StudentAttendance> getStudentAttendance()
    {
        return dalm.getStudentAttendance();
    }

    public List<StudentAttendance> getStudentAttendanceByDate(Date date)
    {
        return dalm.getStudentByDate(date);
    }

    public List<Week> getWeek()
    {
        return dalm.getWeek();
    }

    public List<AClass> getAllClasses()
    {
        return dalm.getAllClasses();
    }

    public boolean studentLogin(String user, String password)
    {
        return dalm.studentLogin(user, password);
    }

    public boolean teacherLogin(String user, String password)
    {
        return dalm.teacherLogin(user, password);
    }

    
    public List<StudentAttendance> getAllStudentsInClass(int selectedId)
    {
        return dalm.getAllStudentsInClass(selectedId);
    }

    public void editAttendance(StudentAttendance sA)
    {
        dalm.editStudentAttendance(sA);
    }
}
