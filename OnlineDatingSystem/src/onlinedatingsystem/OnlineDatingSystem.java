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
public class OnlineDatingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String selection = "";
        
        while(!selection.equals("4"))
        {
            System.out.println("Please make a selection");
            System.out.println("1: New user? Signup");
            System.out.println("2: Login");
            System.out.println("3: Forgot password?");
            System.out.println("4: Exit application");
            Scanner input = new Scanner(System.in);
            selection = input.next();
            System.out.println("");
            if(selection.equals("1"))
            {
               signUp(); 
            }
            else if(selection.equals("2"))
            {
               UserLogin.login(); 
            }
            else if(selection.equals("3"))
            {
                ForgotPassword.forgotPsw();
            }            
        }
    }
    public static void signUp()
    {
        String loginID;
        String psw;
        String gender;
        int age;
        String city;
        String interest1;
        String interest2;
        String interest3;
        String securityques;
        String securityans;
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a login ID:");
        loginID = input.next();
        System.out.println("Enter a password:");
        psw = input.next();
        System.out.println("Enter your gender - M or F:");
        gender = input.next();
        System.out.println("Enter your age:");
        age = input.nextInt();
        System.out.println("Enter your city:");
        city = input.next();
        System.out.println("Enter your first interest:");
        interest1 = input.next();
        System.out.println("Enter your second interest:");
        interest2 = input.next();
        System.out.println("Enter your third interest:");
        interest3 = input.next();
        System.out.println("Enter your security question:");
        Scanner input1 = new Scanner(System.in);
        securityques = input1.nextLine();
        System.out.println("Enter your security answer:");
        securityans = input1.nextLine();
        
        final String databaseURL = "jdbc:mySQL://mis-sql.uhcl.edu/ganesans6017";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(databaseURL,"ganesans6017","1421254");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from userprofile where loginID = '"+loginID+"'");
            if(rs.next())
            {
                System.out.println("***User creation failed, User already exists!***");
            }
            else
            {
//            String query = "insert into userprofile(loginID,psw,gender,age,city,i1,i2,i3) values ('" 
//                   + loginID + "','"+ psw+"','"+gender+"',"+age+",'"+city+"','"
//                    +interest1+"','"+interest2+"','"+interest3+"')";
//            stmt.executeUpdate(query);
                int r = stmt.executeUpdate("insert into userprofile(loginID,psw,gender,age,city,i1,i2,i3,lastlogin,securityques,securityans) values"
                        + "('" + loginID + "','"+ psw+"','"+gender+"',"+age+",'"+city+"','"
                    +interest1+"','"+interest2+"','"+interest3+"','"+DateAndTime.dateTime()+"','"+securityques+"','"+securityans+"')");
                System.out.println("***New user creation successful!!!***");            
            }            
        }
        catch(SQLException e)
        {
            System.out.println("***Sign up failed***");
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
