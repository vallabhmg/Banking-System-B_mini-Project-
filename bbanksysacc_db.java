 /*
In this Code User Can Create a New Bank Account and Check the Account Number if He/She forget By Entering Credentials.In This there Are Function/Method which are as follow:-
*/

package Banking_System_Project;
import java.sql.*;
import java.util.Scanner;

public class bbanksysacc_db{
private Connection con;
private Scanner inp;

public bbanksysacc_db(Connection con,Scanner inp){
this.con=con;
this.inp=inp;
}

public long openacc(String uemail){
if(!accountexits(uemail)){
String openacc_query="INSERT INTO vtmbank_acc(uaccno, ufull_name, uemail, balance, usecurity_pin) VALUES(?, ?, ?, ?, ?)";
inp.nextLine();
System.out.println("\nEnter Full Name:");
String uname=inp.nextLine();
System.out.println("\nEnter Amount in Account:");
double balamt=inp.nextDouble();
inp.nextLine();
System.out.println("Enter Security Pin:");
String spin=inp.nextLine();

try{
long uaccountno=genarateaccnum();

PreparedStatement pstat=con.prepareStatement(openacc_query);
pstat.setLong(1,uaccountno);
pstat.setString(2,uname);
pstat.setString(3,uemail);
pstat.setDouble(4,balamt);
pstat.setString(5,spin);
int rowsAffected=pstat.executeUpdate();

if(rowsAffected > 0){
return uaccountno;
}else{
throw new RuntimeException("Account Creation Failed!");
}

}catch(SQLException e){
System.out.println("\nOOPs Error Occurrs");
System.out.println(e.getMessage());
}
}
throw new RuntimeException("Account Creation Failed!!");
}



public long getacc_number(String uemail){
String query="SELECT uaccno FROM vtmbank_acc WHERE uemail = ?";

try{
PreparedStatement pstat=con.prepareStatement(query);
pstat.setString(1,uemail);
ResultSet rans=pstat.executeQuery();

if(rans.next()){
return rans.getLong("uaccno");
}

}catch(SQLException e){
System.out.println("\nOOPs Error Occurrs");
System.out.println(e.getMessage());
}
return 0;
}




public long genarateaccnum(){
try{
Statement stat=con.createStatement();
ResultSet rans=stat.executeQuery("SELECT uaccno FROM vtmbank_acc ORDER BY uaccno DESC LIMIT 1");

if(rans.next()){
long lastuaccno=rans.getLong("uaccno");
return lastuaccno + 1;
}else{
return 297350;
}  

}catch(SQLException e){
System.out.print("Error Occurs");
System.out.print(e.getMessage());
}
return 0;
}



public boolean accountexits(String uemail){
String query="SELECT * FROM vtmbank_acc WHERE uemail = ?";

try{
PreparedStatement pstat=con.prepareStatement(query);
pstat.setString(1,uemail);
ResultSet rans=pstat.executeQuery();
if(rans.next()){
return true;
}else{
return false;
}
}catch(SQLException e){
System.out.println("\nOOPS Error Occurs in account exits");
System.out.println(e.getMessage());
}
return false;
}




}