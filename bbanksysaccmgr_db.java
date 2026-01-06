/* 
This is a Class which provide Functionallity to the User to perform Bank Transactions 
Following are the Methods to Allows the User for Transactions are as follow:-
*/

import java.util.Scanner;
import java.sql.*;

public class bbanksysaccmgr_db{

private Connection con;
private Scanner inp;

bbanksysaccmgr_db(Connection con,Scanner inp){
this.con=con;
this.inp=inp; 
}

public void creditmoney{


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
PreparedStatement pstat=con.prepareStatement("SELECT * FROM vtmbank_acc WHERE uaccno = ? AND uspin = ?");
pstat.setLong(1,uaccno);
pstat.setString(2,uspin);
ResultSet rans=pstat.executeQuery();

if(rans.next()){
double ucurrentbal=ans.getDouble(ubalance);
if(damount<ucurrentbal){
String creditquery="UPDATE vtmbank_acc SET ubalance = ubalance - ? WHERE uaccno = ?";
PreparedStatement pstat=con.prepareStatement(creditquery);
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
System.out.println(e.getMesssage);
}
con.setAutoCommit(true);
}



public void transfermoney{

}

public void getBalance{

}

}