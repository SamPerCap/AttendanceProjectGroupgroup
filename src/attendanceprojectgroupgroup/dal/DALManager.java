/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.dal;

import attendanceprojectgroupgroup.be.Attendance;
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

    public List<Attendance> getAttendance()
    {
        List<Attendance> allAttendance = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Attendance");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Attendance a = new Attendance();
                a.setId(rs.getInt("id"));
                a.setStudentId(rs.getInt("studentId"));
                a.setDate(rs.getString("date"));
                a.setAttendance(rs.getString("Attendance"));
                
                allAttendance.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allAttendance;
    }
}
