/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedatingsystem;

import java.util.ArrayList;

/**
 *
 * @author Sowmith Daram
 */
public class User {
    
    private String loginID;
    private String gender;
    private int age;
    private String city;
    private String interest1;
    private String interest2;
    private String interest3; 

  public User(String log,String gen,int a,String ci,String i1,String i2,String i3)
  {
      this.loginID = log;
      this.gender = gen;
      this.age = a;
      this.city = ci;
      this.interest1 = i1;
      this.interest2 = i2;
      this.interest3 = i3;
  }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }
    
}
