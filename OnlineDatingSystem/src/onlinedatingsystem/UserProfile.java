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
public class UserProfile {

    public void profile() {
        //after login, user profile menu
        Scanner input = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("6")) 
        {
            System.out.println("Please make a selection");
            System.out.println("1: Search");
            System.out.println("2: Friends list");
            System.out.println("3: Message");
            System.out.println("4: View requests");
            System.out.println("5: Most popular profiles");
            System.out.println("6: Log out");
            selection = input.nextLine();
            System.out.println("");
            if (selection.equals("1")) 
            {
                Search s = new Search();
                s.searchUser();
            } 
            else if (selection.equals("2")) 
            {
                FriendsList.friends();
            } 
            else if (selection.equals("3")) 
            {
                Message.message();
            } 
            else if (selection.equals("4")) 
            {
                String select = "";
                Scanner input1 = new Scanner(System.in);
                while (!select.equals("3")) 
                {
                    System.out.println("1: View pending requests");
                    System.out.println("2: view sent requests");
                    System.out.println("3: Go back");
                    select = input.nextLine();
                    System.out.println("");
                    if (select.equals("1")) 
                    {

                        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/darams7291";
                        Connection conn = null;
                        Statement stmt = null;
                        Statement stmt1 = null;
                        ResultSet rs = null;

                        try 
                        {
                            conn = DriverManager.getConnection(databaseURL, "darams7291", "1463877");
                            stmt = conn.createStatement();
                            stmt1 = conn.createStatement();

                            rs = stmt.executeQuery("select * from friendrequest where receiverID = '"
                                    + UserLogin.userID + "' and status = 'pending'");
                            while (rs.next()) 
                            {
                                System.out.println("Friend request from " + rs.getString(1) + " is " + rs.getString(3));
                            }
                            rs.last();
                            if (rs.getRow() == 0) 
                            {
                                System.out.println("No pending requests");
                                System.out.println("");
                            } 
                            else 
                            {
                                System.out.println("");
                                System.out.println("Approve/Deny requests --");
                                System.out.println("Enter user ID");
                                String userID = input.nextLine();
                                System.out.println("Approve/Deny??");
                                String reply = input.nextLine();
                                if (reply.equalsIgnoreCase("Approve")) 
                                {
                                    String query1 = "update friendrequest set status = 'approved' where receiverID = '"
                                            + UserLogin.userID + "' and senderID = '" + userID + "'";
                                    stmt.executeUpdate(query1);
                                } 
                                else 
                                {
                                    String query3 = "update friendrequest set status = 'denied' where receiverID = '"
                                            + UserLogin.userID + "' and senderID = '" + userID + "'";
                                    stmt.executeUpdate(query3);
                                }

                                //updating friendlist table
                                if (reply.equalsIgnoreCase("approve")) 
                                {
                                    String query2 = "insert into friendlist values ('" + UserLogin.userID + "','" + userID + "')";
                                    stmt1.executeUpdate(query2);
                                    System.out.println("***You(" + UserLogin.userID + ") and " + userID + " are now friends***");
                                }
                            }
                        } 
                        catch (SQLException e) {
                            e.printStackTrace();
                        } 
                        finally {
                            try {
                                rs.close();
                                stmt.close();
                                stmt1.close();
                                conn.close();
                            } 
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (select.equals("2")) //view sent requests
                    {
                        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/darams7291";
                        Connection conn = null;
                        Statement stmt = null;                        
                        ResultSet rs = null; 
                        try
                        {
                            conn = DriverManager.getConnection(databaseURL, "darams7291", "1463877");
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery("select * from friendrequest where senderID = '"
                                    + UserLogin.userID + "'");
                            while (rs.next()) 
                            {
                                System.out.println("Friend request to " + rs.getString(2) + " is " + rs.getString(3));                                
                            }
                            System.out.println("");
                            rs.last();
                            if (rs.getRow() == 0) 
                            {
                                System.out.println("No requests sent");
                                System.out.println("");
                            } 
                            
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                rs.close();
                                stmt.close();                                
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }                    
                }
            }
            else if (selection.equals("5"))
            {
                ProfileRanking.ranklist();
            }
        }
    }
}
