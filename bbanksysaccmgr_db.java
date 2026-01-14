/* 
This is a Class which provide Functionallity to the User to perform Bank Transactions 
Following are the Methods to Allows the User for Transactions are as follow:-
*/

package Banking_System_Project;
import java.util.Scanner;
import java.sql.*;

public class bbanksysaccmgr_db{

private Connection con;
private Scanner inp;

bbanksysaccmgr_db(Connection con,Scanner inp){
this.con=con;
this.inp=inp; 
}

public void creditmoney(long uaccno) throws SQLException{
inp.nextLine();
System.out.println("\nEnter Amount:");
double camount=inp.nextDouble();
inp.nextLine();
System.out.println("\nEnter Security Pin:");
String uspin=inp.nextLine();
try{
con.setAutoCommit(false);
if(uaccno!=0){
PreparedStatement pstat=con.prepareStatement("SELECT * FROM vtmbank_acc WHERE uaccno = ? AND usecurity_pin = ?");
pstat.setLong(1,uaccno);
pstat.setString(2,uspin);
ResultSet rans=pstat.executeQuery();

if(rans.next()){
String creditquery="UPDATE vtmbank_acc SET balance = balance + ? WHERE uaccno = ?";
pstat=con.prepareStatement(creditquery);
pstat.setDouble(1,camount);
pstat.setLong(2,uaccno);
int rowsaffected=pstat.executeUpdate();
if(rowsaffected >0){
System.out.println("\nAmount Credited Successfully");
con.commit();
con.setAutoCommit(true);
return;
}else{
System.out.println("\nOpps Sorry Transaction Failed");
con.rollback();
con.setAutoCommit(true);
}
}else{
System.out.println("\nInvalid Pin!!!");
}
}
}catch(SQLException e){
System.out.println("Opps Error Occurs!");
System.out.println(e.getMessage());
}
con.setAutoCommit(true);


}

public void debitmoney(long uaccno) throws SQLException{
inp.nextLine();
System.out.println("\nEnter Amount:");
double damount=inp.nextDouble();
inp.nextLine();
System.out.println("\nEnter Security Pin:");
String uspin=inp.nextLine();

try{
con.setAutoCommit(false);
if(uaccno!=0){
PreparedStatement pstat=con.prepareStatement("SELECT * FROM vtmbank_acc WHERE uaccno = ? AND usecurity_pin = ?");
pstat.setLong(1,uaccno);
pstat.setString(2,uspin);
ResultSet rans=pstat.executeQuery();

if(rans.next()){
double ucurrentbal=rans.getDouble("balance");
if(damount<ucurrentbal){
String debitquery="UPDATE vtmbank_acc SET balance = balance - ? WHERE uaccno = ?";
pstat=con.prepareStatement(debitquery);
pstat.setDouble(1,damount);
pstat.setLong(2,uaccno);

int rowsaffected=pstat.executeUpdate();

if(rowsaffected >0){
System.out.println("\nAmount Debited Successfully");
con.commit();
con.setAutoCommit(true);
return;
}else{
System.out.println("\nOpps Sorry Transaction Failed");
con.rollback();
con.setAutoCommit(true);
}
}else{
System.out.println("\nInsufficient Balance!!!");
}
}else{
System.out.println("\nInvalid Pin!!!");
}
}
}catch(SQLException e){
System.out.println("Opps Error Occurs!");
System.out.println(e.getMessage());
}
con.setAutoCommit(true);
}



public void transfermoney(long sender_accno) throws SQLException{
inp.nextLine();
System.out.println("\nEnter Receiver Account Number:");
long receiver_accno =inp.nextLong();
System.out.print("Enter Amount:");
double amount = inp.nextDouble();
inp.nextLine();
System.out.print("Enter Security Pin:");
String spin=inp.nextLine();

try{
con.setAutoCommit(false);
	
if(sender_accno !=0 && receiver_accno !=0){
PreparedStatement pstat=con.prepareStatement("SELECT * FROM vtmbank_acc WHERE uaccno = ? AND usecurity_pin = ?");
pstat.setLong(1,sender_accno);
pstat.setString(2,spin);
ResultSet rans=pstat.executeQuery();

if(rans.next()){
double current_bal=rans.getDouble("balance");
if(amount<=current_bal){
String debit_query="UPDATE vtmbank_acc SET balance=balance - ? WHERE uaccno=?";
String credit_query="UPDATE vtmbank_acc SET balance=balance + ? WHERE uaccno=?";
PreparedStatement dpstat=con.prepareStatement(debit_query);
PreparedStatement cpstat=con.prepareStatement(credit_query);
dpstat.setDouble(1,amount);
dpstat.setLong(2,sender_accno);

cpstat.setDouble(1,amount);
cpstat.setLong(2,receiver_accno);

int senderrowAffect= dpstat.executeUpdate();
int receiverrowAffect= cpstat.executeUpdate();
if(senderrowAffect > 0 && receiverrowAffect > 0){
System.out.println("transaction Successful!");
System.out.println("RS"+amount+"Transferred Successfully");
con.commit();
con.setAutoCommit(true);
return;
}else{
System.out.println("\nTransaction Failed");
con.rollback();
con.setAutoCommit(true);
}
}else{
System.out.println("\nInsufficient Balance!!");
}
}else{
System.out.println("Invalid Security Pin !!!");
}
}else{
System.out.println("\nInvalid Account Number");
}
}catch(SQLException e){
e.printStackTrace();
}
con.setAutoCommit(true);
}


public void getBalance(long uaccno){
inp.nextLine();
System.out.println("\nEnter Security Pin:");
String spin=inp.nextLine();
String query="SELECT balance FROM  vtmbank_acc WHERE uaccno = ? AND usecurity_pin = ?";

try{
PreparedStatement pstat=con.prepareStatement(query);
pstat.setLong(1,uaccno);
pstat.setString(2,spin);

ResultSet rans=pstat.executeQuery();
if(rans.next()){
double balance=rans.getDouble("balance");
System.out.println("Balance:"+balance);
}else{
System.out.println("\nError You Entered Invalid Pin");
}
}catch(SQLException e){
e.printStackTrace();
}
}

}