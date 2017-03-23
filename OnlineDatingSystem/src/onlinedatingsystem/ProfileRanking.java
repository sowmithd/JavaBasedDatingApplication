/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Sowmith Daram
 */
public class ProfileRanking {
    
    public static void ranklist()
    {
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();
            System.out.println("***Most popular Males***");
            String query1 = "select * from userprofile where gender = 'M' order by views desc";
            rs = stmt.executeQuery(query1);
            int count = 0;
            while(rs.next())
            {
                if(count<3)
                {
                    System.out.println(rs.getString(1));
                    count++;
                }
                else
                {
                    break;
                }
            }
            System.out.println("***Most popular Females***");
            String query2 = "select * from userprofile where gender = 'F' order by views desc";
            rs = stmt.executeQuery(query2);
            int count1 = 0;
            while(rs.next())
            {
                if(count1<3)
                {
                    System.out.println(rs.getString(1));
                    count1++;
                }
                else
                {
                    break;
                }
            }
            String user = "";
            while(true)
            {
            System.out.println("To view any user profile, enter user ID --");
            Scanner input = new Scanner(System.in);
            user = input.next();
            //viewing another user's profile 
            String query3 = "select * from userprofile where loginID = '"+ user + "'";
            rs = stmt.executeQuery(query3);
            rs.last();
            if(rs.getRow()==0)
            {
                System.out.println("Invalid user!Please try again...");
            }
            else
            {
                break;
            }
            }
            rs.beforeFirst();
            while(rs.next())
            {                               
                System.out.println("Profile of " + rs.getString(1));                
                System.out.println("Gender: " + rs.getString(3));
                System.out.println("Age: " + rs.getInt(4));
                System.out.println("City: "+ rs.getString(5));
                System.out.println("Interests: " + rs.getString(6)+", "+rs.getString(7)+", "+rs.getString(8));                
                System.out.println("Last login @ "+ rs.getString(9));
                System.out.println("Profile viewed: "+ rs.getString(10) +" times");
            }
            
            String query4 = "select views from userprofile where loginID = '" + user +"'";
            rs = stmt.executeQuery(query4);
            rs.next();
            int count2 = rs.getInt(1);
            count2 = count2+1;
            String query5 = "update userprofile set views = '"+count2+"' where loginID = '"+ user +"'";
            stmt.executeUpdate(query5);
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
