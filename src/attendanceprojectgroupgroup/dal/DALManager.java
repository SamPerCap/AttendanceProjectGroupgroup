/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.dal;

import attendanceprojectgroupgroup.be.AClass;
import attendanceprojectgroupgroup.be.Student;
import attendanceprojectgroupgroup.be.Week;
import attendanceprojectgroupgroup.be.StudentAttendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
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

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT DISTINCT s.id, s.name, a.attendance"
                    + " FROM Attendance a, Student s"
                    + " WHERE a.studentID = s.id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StudentAttendance a = new StudentAttendance();

                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setAttendance(50f);
                a.setPresence(rs.getString("attendance"));

                allStudentAttendance.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
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
//            w.setMonday("Here");
//            w.setTuesday("Here");
//            w.setWednesday("Here");
//            w.setThursday("Here");
//            w.setFriday("Here");

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
    public List<AClass> getAllClasses()
    {
        System.out.println("Getting all Classes.");

        List<AClass> allClasses = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Class");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                AClass c = new AClass();

                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setTeacherId(rs.getInt("teacherId"));

                allClasses.add(c);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class
                    .getName()).log(
                            Level.SEVERE, null, ex);
        }
        return allClasses;
    }
    public void editStudentAttendance(StudentAttendance sA)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Attendance SET attendance=? "
                    + " WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, sA.getPresence());
            pstmt.setInt(2, sA.getId());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Student could not be edited");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * This method gets all the movies on a list way.
     *
     * @return
     */
    public List<Student> getAllStudents()
    {
        System.out.println("Getting all Students.");

        List<Student> allStudents = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Student");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));

                allStudents.add(s);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allStudents;
    }

    public List<StudentAttendance> getStudentByDate(Date date)
    {
        List<StudentAttendance> allStudentAttendanceDates = new ArrayList();
        
        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Student.id, Student.name, Attendance.attendance, Attendance.date "
                    + "FROM Student "
                    + "JOIN Attendance ON Student.id = Attendance.studentId "
                    + "WHERE Attendance.date = ?");

            stmt.setDate(1, (java.sql.Date) date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StudentAttendance a = new StudentAttendance();

                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setAttendance(0f);
                a.setDate(rs.getString("date"));
                a.setPresence(rs.getString("attendance"));

                allStudentAttendanceDates.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return allStudentAttendanceDates;
    }

    /**
     *
     * Gets all students from selected Class
     */
    public List<Student> getAllStudentsInClass(int selectedId)
    {
        List<Student> allStudentsInClass = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    " SELECT * "
                    + " FROM ((StudentClass "
                    + " JOIN Class ON StucentClass.classId = Class.id) "
                    + " JOIN Student ON StudentClass.studentId = Student.id) "
                    + " WHERE Class.id = ? "
            );

            stmt.setInt(1, selectedId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                //     m.setAttendingClass(rs.getString("password"));

                allStudentsInClass.add(s);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allStudentsInClass;
    }

}
