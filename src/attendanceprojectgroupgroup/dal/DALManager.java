/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.dal;

import attendanceprojectgroupgroup.be.Attendance;
import attendanceprojectgroupgroup.be.StudentAttendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class DALManager
{
    private ConnectionManager cm = new ConnectionManager();

    public List<StudentAttendance> getStudentAttendance()
    {
        System.out.println("Getting attendance");
        
        List<StudentAttendance> allStudentAttendance = new ArrayList();
        
        for (int i = 0; i < 9; i++)
        {
            StudentAttendance a = new StudentAttendance();
            a.setId(i);
            a.setName("student" + i);
            a.setAttendance(50f);
            a.setPresence("here");
            
            allStudentAttendance.add(a);
        }
        
//        List<StudentAttendance> allStudentAttendance = new ArrayList();
//
//        try (Connection con = cm.getConnection())
//        {
//            PreparedStatement stmt = con.prepareStatement("");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next())
//            {
//                StudentAttendance a = new StudentAttendance();
//                a.setId(rs.getInt("id"));
//                a.setName(rs.getString("name"));
//                
//                //a.setAttendance(rs.getFloat("Attendance"));
//                
//                allStudentAttendance.add(a);
//            }
//        } catch (SQLException ex)
//        {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
        return allStudentAttendance;
    }
}
