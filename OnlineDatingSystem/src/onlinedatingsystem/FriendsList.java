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
 * @author GanesanS6017
 */
public class FriendsList {
    
    public static void friends()
    {
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("select * from friendlist where (friend1 = '" + UserLogin.userID
                 +"') or (friend2 = '" + UserLogin.userID +"')");
            rs.last();
            if(rs.getRow()==0)
            {
                System.out.println("***You haven't added any friends yet***");
            }
            else
            {
            rs.beforeFirst();
            while(rs.next())
            {
                if(!rs.getString(1).equals(UserLogin.userID))
                System.out.println(rs.getString(1));
            
                if(!rs.getString(2).equals(UserLogin.userID))
                System.out.println(rs.getString(2));
            }           
            
            //viewing another user's profile
            String user = "";
            while(true)
            {
            System.out.println("To view any user profile, enter user ID --");
            Scanner input = new Scanner(System.in);
            user = input.next();
            String query1 = "select * from userprofile where loginID = '"+ user + "'";
            rs = stmt.executeQuery(query1);
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
            
            String query2 = "select views from userprofile where loginID = '" + user +"'";
            rs = stmt.executeQuery(query2);
            rs.next();
            int count = rs.getInt(1);
            count = count+1;
            String query3 = "update userprofile set views = '"+count+"' where loginID = '"+ user +"'";
            stmt.executeUpdate(query3);
            System.out.println();
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
