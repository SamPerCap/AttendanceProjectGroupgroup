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

    /**
     *
     * @return List composed by information about StudentAttendance
     */
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
                a.setAttendance(0f);
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

    /**
     *
     * @return It takes a list composed by week number. From 1 to 8, not very
     * real data
     */
    public List<Week> getWeek()
    {
        System.out.println("Getting attendance");

        List<Week> allWeek = new ArrayList();

        for (int i = 1; i < 9; i++)
        {
            Week w = new Week();
            w.setWeekNumber(i);
            allWeek.add(w);
        }
        return allWeek;
    }

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

    /**
     *
     * @param sA Able to edit the student's attendance
     */
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
     *
     * @return List composed by Student's information
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
     * Gets all students from selected Class by joining all tables together
     */
    public List<StudentAttendance> getAllStudentsInClass(int selectedId)
    {
        List<StudentAttendance> allStudentsInClass = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    " SELECT DISTINCT Student.id, Student.name, Attendance.attendance"
                    + " FROM (((StudentClass "
                    + " JOIN Class ON StudentClass.classId = Class.id) "
                    + " JOIN Student ON StudentClass.studentId = Student.id)"
                    + " JOIN Attendance ON StudentClass.studentId = Attendance.studentId)"
                    + " WHERE Class.id = ? "
            );

            stmt.setInt(1, selectedId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StudentAttendance a = new StudentAttendance();

                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setAttendance(0f);
                a.setPresence(rs.getString("attendance"));

                // allStudentAttendance.add(a);
                allStudentsInClass.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allStudentsInClass;
    }

    public boolean studentLogin(String user, String password)
    {

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT name, password FROM Student WHERE name = ? AND password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return true;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    public boolean teacherLogin(String user, String password)
    {

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT name, password FROM Teacher WHERE name = ? AND password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return true;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Everytime a student press a button to change attendance. It registers the
     * change to 1
     */
    public void registerChange()
    {
        int number = 1;
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Attendance SET studentChange =1 "
                    + " WHERE studentId=1";

            PreparedStatement pstmt = con.prepareStatement(sql);

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
     * Created to teacher. Teacher sees a student made a change -> Teacher
     * refueses or accepts Then the student change is setting to 0
     */
    public void cancelChange(int ID)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Attendance SET studentChange = 0"
                    + " WHERE studentId=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, ID);

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Register could not be edited");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return Every student that made a change in their own attendance
     */
    public List<Integer> getStudentChange()
    {
        List<Integer> allChanges = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT DISTINCT studentID FROM Attendance WHERE studentChange = 1");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                allChanges.add(rs.getInt("studentID"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allChanges;
    }
}
