/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Sowmith Daram
 */
public class DateAndTime {
    
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    
    public static String dateTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
}
