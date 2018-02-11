/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.model;

import attendanceprojectgroupgroup.bll.BLLManager;

/**
 *
 * @author Samuel
 */
public class AttendanceModel
{
    BLLManager bll = new BLLManager();
    
    public void loadAttendance()
    {
        bll.loadAttendance();
    }
    
}
