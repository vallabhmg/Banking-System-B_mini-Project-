/*This Code of Mini Project of Banking System(Bank Name = vtm bank),this is a console base(Terminal Base Application to Open Account in Bank) Program.
#1)Account Class#In this user can open an account on in vtm bank and also Handle the User Account in the Database.It can also manupulate the account
#2)User#In this user have to register on Website(i.e on vtm Bank)
#3)Account Manager#In this user can perform Trasanction of money and also Check the  Available Balance
#4)Banking App#In this,the main Class is Present and handle the all above classes 


#package Banking_System_Project;
import java.sql.*;
import java.util.Scanner;
public class bbanksysapp_db{
private static final String url="jdbc:mysql://localhost:3306/vtmbank_sys";
private static final String username="root";
private static final String password="rootadmin15";
public static void main(String args) throws ClassNotFoundException, SQLException {
try{
Class.forName("com.mysql.cj.jdbc.Driver");
System.out.print("\nDriver Loaded Successfully\n");
}catch(ClassNotFoundException e{
System.out.println("\nOOPS ErrorOccurs"); 
System.out.println(e.getmessage());
}
try{
Connection con=DriverManager.getConnection(url,usrname,password);
Scanner inp =new Scanner(System.in);

Accounts usersaccount = new Accounts(con,inp);
AccountsManager usersmanager= new AccountsManager(con,inp);

String email;
long account_number;
while(true){
System.out.println("\n***** WELCOME TO THE ONLINE VTM BANKING SYSTEM *****\n");
System.out.println();
System.out.println("<Button>(1) Register");
System.out.println("<Button>(2) Login");
System.out.println("<Button>(3) Exit");
System.out.println("Enter Your Button Choice:");
int choice1 = inp.nextInt();
switch(choice1){
case 1:
user.register();

}
}
}catch(){


}




}
}