/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.bll;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.be.StudentAttendance;
import attendanceprojectgroupgroup.dal.DALManager;
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
    
}
