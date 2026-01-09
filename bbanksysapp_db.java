/*This Code of Mini Project of Banking System(Bank Name = vtm bank),this is a console base(Terminal Base Application to Open Account in Bank) Program.
#1)Account Class#In this user can open an account on in vtm bank and also Handle the User Account in the Database.It can also manupulate the account
#2)User#In this user have to register on Website(i.e on vtm Bank)
#3)Account Manager#In this user can perform Trasanction of money and also Check the  Available Balance
#4)Banking App#In this,the main Class is Present and handle the all above classes 
*/

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

bbanksysacc_db accounts=new bbanksysacc_db(con,inp);
bbanksysaccmgr_db accManager= new bbanksysaccmgr_db(con,inp);
bbanksysuser_db user=new bbanksysuser_db(con,inp);

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
System.out.flush();
break;

case 2:
email=user.login();
if(email!=null){
System.out.println();
System.out.println("User Logged In!);
if(!accounts.accountexits(email)){
System.out.println();
System.out.println("1>-Open a new Bank Account");
System.out.println("2>-Exit");
if(inp.nextInt()==1){
account_number=accounts.openacc(email);
System.out.println("Account is Created Successfully");
System.out.println("Your Account Number is:",account_number);
}else{
break;
}
}
account_number=accounts.getacc_number(email);
int choice2=0;
while(choice2 != 5){
System.out.println();
System.out.println("1>-Debit Money");
System.out.println("2>-Credit Money");
System.out.println("3>-Transfer Money");
System.out.println("4>-Check Balance");
System.out.println("5>-Log Out");
System.out.println("\nEnter Your Choice:");
choice2=inp.nextline();
switch(choice2){
case 1:
accManager.debitmoney(account_number);
break;
case 2:
accManager.creditmoney(account_number);
break;
case 3:
accManager.transfermoney(account_number);
break;
case 4:
accManager.getBalance(account_number);
break;
case 5:
break;
default:
System.out.println("Enter Valid Choice!");
break;
}
}
}
else{
System.out.println("Incorrect Email or Password!!!");
}

case 3:
System.out.println("\nThankyou For Using VTM Banking SYSTEM");
System.out.println("Stay Safe,Be Aware from fake Bank Fraud");
System.out.println("Exiting System!");
return;

defualt:
System.out.println("Enter Valid Choice");
break;
}
}
}catch(SQLExcepetion e){
//e.printStackTrace();
System.out.println(e.getMessage);
}




}//upper switch
}//end of class