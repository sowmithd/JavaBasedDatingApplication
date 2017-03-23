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
public class Message {
    
    public static void message()
    {
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        Connection conn = null;
        Statement stmt = null;        
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();            
            
            String option = "";
            Scanner input = new Scanner(System.in);
            while(!option.equals("4"))
            {
                System.out.println("1: Send Message");
                System.out.println("2: View received messages");
                System.out.println("3: View sent messages");
                System.out.println("4: Go back");
                option = input.nextLine();
                System.out.println("");
                
                if(option.equals("1"))
                {
                    System.out.print("To: ");
                    String receiver = input.nextLine();
                    System.out.println("Type your message:");
                    Scanner input1 = new Scanner(System.in);
                    String content = input1.nextLine();
                    
                    String query1 = "select * from friendlist where (friend1 = '"+ UserLogin.userID +"' and friend2 = '"
                            + receiver +"') or (friend1 = '"+ receiver +"' and friend2 = '"+UserLogin.userID +"')";
                    rs = stmt.executeQuery(query1);
                    if(rs.next())
                    {
                        String query2 = "insert into message values ('" + UserLogin.userID + "','" + receiver
                                + "','" + content + "','" + DateAndTime.dateTime() + "')";
                        stmt.executeUpdate(query2);
                        System.out.println("");
                        System.out.println("***Message sent***");
                    }
                    else
                    {
                        System.out.println("***Message cannot be sent! You both are not friends***");
                    }     
                }
                else if(option.equals("2"))
                {
                    System.out.println("");
                    String query3 = "select * from message where receiverID = '"+ UserLogin.userID +"'";
                    rs = stmt.executeQuery(query3);
                    while(rs.next())
                    {
                        System.out.println("Message: "+rs.getString(3) +"  From: "+rs.getString(1)
                                +" Sent at: "+rs.getString(4));
                    }
                    rs.last();
                    if(rs.getRow()==0)
                    {
                        System.out.println("***There are no messages***");
                    }
                    System.out.println("");
                }
                else if(option.equals("3"))
                {
                    System.out.println("");
                    String query4 = "select * from message where senderID = '" + UserLogin.userID + "'";
                    rs = stmt.executeQuery(query4);
                    while (rs.next()) 
                    {
                        System.out.println("Message: " + rs.getString(3) + "  To: " + rs.getString(2) 
                                + " Sent at: " + rs.getString(4));
                    }
                    rs.last();
                    if(rs.getRow()==0)
                    {
                        System.out.println("***There are no sent messages***");
                    }
                    System.out.println("");
                }
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
