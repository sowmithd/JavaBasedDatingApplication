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
public class ForgotPassword {
    
    public static void forgotPsw()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter login Id");
        String loginID = input.next();
        System.out.println("Enter security question");
        Scanner in1 = new Scanner(System.in);
        String secQues = in1.nextLine();
        System.out.println("Enter security answer");
        Scanner in2 = new Scanner(System.in);
        String secAns = in2.nextLine();
        
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("select * from userprofile where loginID = '"+loginID + "' and securityques = '"
                 + secQues +"' and securityans = '"+ secAns +"'");
            if(rs.next())
            {               
                System.out.println("Enter new password");
                String newPsw = input.next();
                String query = "update userprofile set psw = '" + newPsw + "' where loginID = '" + loginID + "'";
                stmt.executeUpdate(query);
                System.out.println("***Password reset successful***");
            }
            else
            {
                System.out.println("***Incorrect security question & answer***");
            }
            System.out.println("");
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
    public static void logOut() 
    {
        
    }
    
    
}
