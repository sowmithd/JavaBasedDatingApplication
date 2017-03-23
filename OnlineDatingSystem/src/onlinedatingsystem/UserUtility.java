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
public class UserUtility {

    public static void viewUser(String user) {

        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/darams7291";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(databaseURL, "darams7291", "1463877");
            stmt = conn.createStatement();

            //viewing another user's profile 
            String query1 = "select * from userprofile where loginID = '" + user + "'";
            rs = stmt.executeQuery(query1);
            while (rs.next()) {
                System.out.println("Profile of " + rs.getString(1));
                System.out.println("Gender: " + rs.getString(3));
                System.out.println("Age: " + rs.getInt(4));
                System.out.println("City: " + rs.getString(5));
                System.out.println("Interests: " + rs.getString(6) + ", " + rs.getString(7) + ", " + rs.getString(8));
                System.out.println("Last login @ " + rs.getString(9));
                System.out.println("Profile viewed: " + rs.getString(10) + " times");
            }
            rs.last();
            if(rs.getRow()== 0)
            {
                System.out.println("Invalid user!Please try again...");
            }
            String query2 = "select views from userprofile where loginID = '" + user + "'";
            rs = stmt.executeQuery(query2);
            rs.next();
            int count = rs.getInt(1);
            count = count + 1;
            String query3 = "update userprofile set views = '" + count + "' where loginID = '" + user + "'";
            stmt.executeUpdate(query3);

            Scanner input = new Scanner(System.in);
            
            String options = "";

            //checking if they are friends
            String query4 = "select * from friendlist where (friend1 = '" + UserLogin.userID
                    + "' and friend2 = '" + user + "') or (friend1 = '" + user + "' and friend2 = '"
                    + UserLogin.userID + "')";
            rs = stmt.executeQuery(query4);

            if (rs.next()) {
                System.out.println("***You are friends with " + user + " ***");
                System.out.println("");
                System.out.println(" Press any key to go back");
                options = input.next();
            } else {
                String query5 = "select * from friendrequest where senderID = '" + UserLogin.userID
                        + "' and receiverID = '" + user + "'";
                rs = stmt.executeQuery(query5);

                //sending friend request
                if (rs.next()) {
                    if (rs.getString(3).equals("pending")) {
                        System.out.println("***Your friend request is pending***");
                        System.out.println("");
                        System.out.println(" Press any key to go back");
                        options = input.next();
                    } else if (rs.getString(3).equals("denied")) {
                        System.out.println("1.Send friend request");
                        System.out.println("2. Go back");
                         options = input.next();
                        if (options.equals("1")) {
                            String query7 = "update friendrequest set status = 'pending' where senderID = '" + UserLogin.userID
                                    + "' and receiverID = '" + user + "'";
                            stmt.executeUpdate(query7);
                            System.out.println("***Friend request sent***");
                        } else {
                            return;
                        }

                    }
                } else {
                    System.out.println("1.Send friend request");
                    System.out.println("2. Go back");
                    options = input.next();
                    if (options.equals("1")) {
                        String query6 = "insert into friendrequest values ('" + UserLogin.userID
                                + "','" + user + "','pending')";
                        stmt.executeUpdate(query6);
                        System.out.println("***Friend request sent***");
                        System.out.println();
                    }
                    else{
                        return;
                    }
                }
            }

        } catch (SQLException e) {
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
