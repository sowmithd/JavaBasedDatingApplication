/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Sowmith Daram
 */
public class Search {

    public void searchUser() {
        Scanner input = new Scanner(System.in);
        String viewUser = "";
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/darams7291";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, "darams7291", "1463877");
            stmt = conn.createStatement();

            String gender = "";
            String[] age = new String[2];
            int minAge = 0;
            int maxAge = 0;
            String city = "";
            String interest = "";

            System.out.println("Enter gender - M or F or any");
            String gen = input.next();
            if (!(gen.equals("any"))) {
                gender = "('" + gen + "')";
            } else {
                gender = "('M','F')";
            }

            System.out.println("Enter minimum and maximum age e.g., 24,30");
            age = input.next().split(",");

            if (!age[0].equals("any") && !age[1].equals("any")) {
                minAge = Integer.parseInt(age[0]);
                maxAge = Integer.parseInt(age[1]);
            } else {
                rs = stmt.executeQuery("select age from userprofile");

                minAge = 100;
                maxAge = 0;
                while (rs.next()) {
                    if (minAge > rs.getInt(1)) {
                        minAge = rs.getInt(1);
                    }

                    if (maxAge < rs.getInt(1)) {
                        maxAge = rs.getInt(1);
                    }
                }
            }
            System.out.println("Enter city");
            String c = input.next();
            if (!(c.equals("any"))) {
                city = "('" + c + "')";
            } else {
                rs = stmt.executeQuery("select city from userprofile");
                city += "(";
                while (rs.next()) {
                    if (!rs.isLast()) {
                        city += "'" + rs.getString(1) + "',";
                    } else {
                        city += "'" + rs.getString(1) + "')";
                    }
                }

            }

            System.out.println("Enter interest");
            String i = input.next();
            if (!(i.equals("any"))) {
                interest = "('" + i + "')";
            } else {
                rs = stmt.executeQuery("select i1,i2,i3 from userprofile");
                interest += "(";
                while (rs.next()) {
                    if (!rs.isLast()) {
                        interest += "'" + rs.getString("i1") + "','" + rs.getString("i2") + "','" + rs.getString("i3") + "',";
                    } else {
                        interest += "'" + rs.getString("i1") + "','" + rs.getString("i2") + "','" + rs.getString("i3") + "')";
                    }
                }

            }
            String query = "select * from userprofile where (gender in " + gender + " ) and (age >= "
                    + minAge + " and age <= " + maxAge + ") and (city in " + city + ") and (i1 in "
                    + interest + " or i2 in " + interest + " or i3 in " + interest + ") and (loginID != '" + UserLogin.userID + "')";

            rs = stmt.executeQuery(query);
//            rs.last();
//            System.out.println(rs.getRow());
//                rs.beforeFirst();
            System.out.println("***Following are your search results***");
            while (rs.next()) {
                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getInt(4));
//                System.out.println(rs.getString(5));
//                System.out.println(rs.getString(6));
//                System.out.println(rs.getString(7));
//                System.out.println(rs.getString(8));
            }
            rs.last();
            if (rs.getRow() == 0) {
                System.out.println("***Sorry! Your search results are empty.***");
            } else if (rs.getRow() != 0) {
                while (true) {
                    System.out.println("***To view any user profile, please enter the user name:");
                    viewUser = input.next();
                    String query1 = "select * from userprofile where loginID = '" + viewUser + "'";
                    rs = stmt.executeQuery(query1);
                    rs.last();
                    if (rs.getRow() == 0) {
                        System.out.println("Invalid user, please try again");
                    } else {
                        break;
                    }

                }

                UserUtility.viewUser(viewUser);
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
