/*This is an User Class of Banking System(Bank Name = vtm bank).In this class User Can Perform Functions Like Logout,Login,Register.
*/

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SqlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;

public class bbanksysuser_db{
private Connection con;
private Scanner inp;

public bbanksysuser_db(Connection con,Scanner inp){
this con=con;
this inp=inp;
}

public void register(){
inp.nextLine();
System.out.print("Enter Full Name:");
String fullname=inp.nextLine();
System.out.print("Enter Email:");
String email=inp.nextLine();
System.out.print("Enter Password:");
String userpasswd=inp.nextLine();
if(userexist(email)){
System.out.println("User Allready Exists for this Email Address!!");
return;
}
String registerquery="INSERT INTO USER(ufull_name,uemailvtmbank_users,upassword) VALUES(?,?,?)";
try{
#Below,are Statement(Query) Creation
PreparedStatement pstat=con.prepareStatement(registerquery);
pstat.setString(1,fullname);
pstat.setString(2,email);
pstat.setString(3,userpasswd);
#Below,are Executing the Query
int rowsaffected=pstat.executeUpdate();
if(rowsaffected > 0){
System.out.println("Registration Successfull");
}else{
System.out.println("Registration Failed");
}
}catch(SQLException e){
System.out.print("\nOOPs Error Occurrss\n");
System.out.print("\ne.getMessage()");
}
}

public String login(){
inp.nextline();
System.out.print("Enter Email");
String uemail=inp.nextLine();
System.out.print("Enter Password");
String upasswd=inp.nextLine();
String loginquery="SELECT * FROM vtmbank_users WHERE uemailvtmbank_users = ? AND upassword = ?";
try{
PreparedStatement pstat=con.prepareStatement(loginquery);
pstat.setString(1,uemail);
pstat.setString(2,upassword);
ResultSet rans=pstat.excuteQuery();
if(rans.next()){
return email;
}else{
return null;
}
}catch(SQLException e){
System.out.print("\nOOPs Error Occurrss\n");
System.out.print("\ne.getMessage()");
}
return null;
}


public boolean userexist(String uemail){
String userexquery="Select * FROM user WHERE uemailvtmbank_users= ?";
try{
PreparedStatement pstat=con.prepareStatement(userexquery);
pstat.setString(1,uemail);
ResultSet rans=pstat.executeQuery();
if(rans.next()){
return true;
}else{
return false;
}
}catch(SQLException e){
e.printStackTrace();
}
return false; 
}
//

//This is an first class closing}