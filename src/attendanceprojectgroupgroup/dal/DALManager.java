/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.dal;

import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.be.StudentAttendance;
import java.util.ArrayList;
import java.util.List;

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
        }
        return allStudentAttendance;
    }
    
    public List<Week> getWeek()
    {
        System.out.println("Getting attendance");

        List<Week> allWeek = new ArrayList();

        for (int i = 1; i < 9; i++)
        {
            Week w = new Week();
                w.setWeekNumber(i);
                w.setMonday("here");
                w.setTuesday("here");
                w.setWednesday("here");
                w.setThursday("here");
                w.setFriday("here");

            allWeek.add(w);
        }
        return allWeek;
    }

//    public List<Attendance> getAttendance() {
//        System.out.println("Getting attendance");
//
//        List<Attendance> allAttendance = new ArrayList();
//
//        try (Connection con = cm.getConnection()) {
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Attendance");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Attendance a = new Attendance();
//                a.setId(rs.getInt("id"));
//                a.setStudentId(rs.getInt("studentId"));
//                a.setDate(rs.getString("date"));
//                a.setAttendance(rs.getString("attendance"));
//
//                allAttendance.add(a);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
}
