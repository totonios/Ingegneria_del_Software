/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
/**
 *
 * @author Alfredo
 */
public class WriteToFullState {
    
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost/mydb";

   //  Database credentials
        static final String USER = "root";
        static final String PASS = "";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
  
    
    } 



public static void fetchandwriteonfull () throws ClassNotFoundException, SQLException {
   Connection conn = null;
   Statement stmt = null;
   Statement stmtf = null;
 
      //STEP 2: Register JDBC driver
     Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
       
       
      stmtf = (Statement) conn.createStatement();
      String sqlf = "SELECT DISTINCT idrobot FROM `calcolo`";
      ResultSet rsf = stmtf.executeQuery(sqlf);
      Timestamp date = null;
      int nullstate = 42;
      while(rsf.next()){
         int s1 = nullstate;
         int s2 = nullstate;
         int s3 = nullstate;   //mi serve per identificare se null, cosi copio valori di prima
         int s4 = nullstate;
         int s5 = nullstate;
         int s6 = nullstate;
         int s7 = nullstate;
       
         
         int robot = rsf.getInt("idrobot");
         String prepf = "SELECT s1, time FROM `calcolo` WHERE s1 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt = conn.prepareStatement(prepf);
         preparedStmt.setInt (1, (robot));
         ResultSet rs = preparedStmt.executeQuery(prepf);
            if(!rs.next()){ 
            }
            else {
                s1 = rs.getInt("s1");
                Timestamp temp = rs.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
            
         String prepf2 = "SELECT s2, time FROM `calcolo` WHERE s2 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt1 = conn.prepareStatement(prepf2);
         preparedStmt1.setInt (1, (robot));
         ResultSet rs1 = preparedStmt1.executeQuery(prepf2);
            if(!rs1.next()){ 
            }
            else {
                s2 = rs1.getInt("s2");
                Timestamp temp = rs1.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
         
         String prepf3 = "SELECT s3, time FROM `calcolo` WHERE s3 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt2 = conn.prepareStatement(prepf2);
         preparedStmt2.setInt (1, (robot));
         ResultSet rs2 = preparedStmt2.executeQuery(prepf3);
            if(!rs2.next()){ 
            }
            else {
                s3 = rs2.getInt("s3");
                Timestamp temp = rs2.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
         
          
      String prepf4 = "SELECT s4, time FROM `calcolo` WHERE s4 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt3 = conn.prepareStatement(prepf4);
         preparedStmt3.setInt (1, (robot));
         ResultSet rs3 = preparedStmt3.executeQuery(prepf);
            if(!rs3.next()){ 
            }
            else {
                s4 = rs3.getInt("s4");
                Timestamp temp = rs3.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
 
            
            String prepf5 = "SELECT s5, time FROM `calcolo` WHERE s5 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt4 = conn.prepareStatement(prepf5);
         preparedStmt4.setInt (1, (robot));
         ResultSet rs4 = preparedStmt.executeQuery(prepf5);
            if(!rs4.next()){ 
            }
            else {
                s5 = rs4.getInt("s5");
                Timestamp temp = rs4.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
            
            String prepf6 = "SELECT s6, time FROM `calcolo` WHERE s6 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt5 = conn.prepareStatement(prepf6);
         preparedStmt5.setInt (1, (robot));
         ResultSet rs5 = preparedStmt1.executeQuery(prepf);
            if(!rs5.next()){ 
            }
            else {
                s6 = rs5.getInt("s1");
                Timestamp temp = rs5.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante, a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
            
            String prepf7 = "SELECT s7, time FROM `calcolo` WHERE s7 IS NOT NULL and idrobot=? ORDER BY `calcolo`.`time` DESC";
         java.sql.PreparedStatement preparedStmt6 = conn.prepareStatement(prepf7);
         preparedStmt6.setInt (1, (robot));
         ResultSet rs6 = preparedStmt6.executeQuery(prepf7);
            if(!rs6.next()){ 
            }
            else {
                s7 = rs6.getInt("s7");
                Timestamp temp = rs6.getTimestamp("time");
                if (date == null || temp.before(date)) {
                  date = temp;
                }//mi serve per fare il truncate della tabella gigante (se non tronco), a fine calcolo gli stati prima del timestamp più vecchio vengono troncati
                }
            
            //adesso?LOL
            //controllo su tab fullstate come stanno messe le cose, se c'è lo stato lo copio, altrimenti lo faccio nuovo e i valori
            //che non ho li metto a 1 yes?
            
         String fullstatecheck = "SELECT s1, s2, s3, s4, s5, s6, s7, time FROM `fullstate` WHERE idrobot=? ORDER BY `fullstate`.`time` DESC";
         java.sql.PreparedStatement preparedStmt7 = conn.prepareStatement(fullstatecheck);
         preparedStmt7.setInt (1, (robot));
         ResultSet rs7 = preparedStmt7.executeQuery(prepf7);
            if(!rs7.next()){ 
              String fullstatenew = "INSERT INTO `fullstate`(`idRobot`, `idCluster`, `s1`, `s2`, `s3`, `s4`, `s5`, `s6`, `s7`, `time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
              java.sql.PreparedStatement preparedStmt8 = conn.prepareStatement(fullstatenew);
              if (s1 != nullstate && s2 !=nullstate && s3 !=nullstate && s4 !=nullstate && s5 !=nullstate && s6 !=nullstate && s7 !=nullstate){
                  //insert tutti i valori
              }
              else {
                  if (s1 == nullstate)
                      s1 = 1;
                  if (s2 == nullstate)
                      s2 = 1;
                  if (s3 == nullstate)
                      s3 = 1;
                  if (s4 == nullstate)
                      s4 = 1;
                  if (s5 == nullstate)
                      s5 = 1;
                  if (s6 == nullstate)
                      s6 = 1;
                  if (s7 == nullstate)
                      s7 = 1;
                  
                 //insert valori 
              }
              }
            
            else 
               if (s1 == nullstate)
                   s1 = rs7.getInt("s1");
                  
               if (s2 == nullstate)
                   s2 = rs7.getInt("s2");
               
               if (s3 == nullstate)
                   s1 = rs7.getInt("s1");
                  
               if (s4 == nullstate)
                   s2 = rs7.getInt("s2");
               
               if (s5 == nullstate)
                   s1 = rs7.getInt("s1");
                  
               if (s6 == nullstate)
                   s2 = rs7.getInt("s2");
               
               if (s7 == nullstate)
                   s1 = rs7.getInt("s1");
               
               //butto valori dentro DB con timestamp attuale (fullstateDB)
      } 
               }
         
            
            
            
            
}
      
    

