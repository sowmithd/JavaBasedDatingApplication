/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem;

import java.util.Scanner;
import java.sql.*;
/**
 *
 * @author Sowmith Daram
 */
public class UserLogin {
    public static String userID;
    public static void login()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter login Id:");
        String loginID = input.next();
        System.out.println("Enter password:");
        String psw = input.next();
        
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from userprofile where loginID = '"+ loginID
                 +"'and psw = '"+ psw +"'");
            if(rs.next())
            {
                
               // rs.next();
                String id = rs.getString(1);
                userID = rs.getString(1);
                String query = "Update userprofile set lastlogin = '" + DateAndTime.dateTime()+ "' where loginID = '" + id +"'";
                stmt.executeUpdate(query);
                UserProfile up = new UserProfile();
                up.profile();
            }
            else
            {
                System.out.println("Invalid login! Please enter the correct user name and password");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                stmt.close();
                conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
