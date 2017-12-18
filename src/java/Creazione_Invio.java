import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.SchedulerException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AD-TEAM
 */



public class Creazione_Invio extends HttpServlet{
   static public ArrayList<String> lista;    
    
  protected void action(HttpServletRequest request,HttpServletResponse response) throws SchedulerException{
      try {
          try {
              Class.forName("com.mysql.jdbc.Driver");
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(Creazione_Invio.class.getName()).log(Level.SEVERE, null, ex);
          }
          //Connessione con il DataBase
          String url = "jdbc:mysql://localhost/mydb";
          try (Connection con = DriverManager.getConnection(url, "root", "root")) {
              String x="select valore from access";
              PreparedStatement preparedStmt0 = con.prepareStatement(x);
              ResultSet result=preparedStmt0.executeQuery();
              int controllo=0;
              while(result.next()){
                  controllo=result.getInt(1);
              }
              if(controllo==0){
              ArrayList<String> rangecluster=new ArrayList<>();
              ArrayList<Cluster> clusters=new ArrayList<>();
              ArrayList<Sensore>lista=new ArrayList<>(7);
              String  sql = "insert into cluster VALUES (?,?)";
              PreparedStatement preparedStmt = con.prepareStatement(sql);
              for (int i=0;i<100;i++){
                  
                  Cluster c=new Cluster(RandomStringUtils.randomAlphabetic(6),100);
                  clusters.add(i, c);
                  rangecluster.add(c.getIDCLUSTER());
                  preparedStmt.setString (1,c.getIDCLUSTER());
                  preparedStmt.setInt (2,c.getIR());
                  preparedStmt.execute();
              }   
              int j=0;
              this.lista=rangecluster;
              //Crea i robot e li mette dentro request
              for (int i = 1; i<90001; i++) {
                  try{
                      
                      String cluster=rangecluster.get(j++);
                      Robot robot=new Robot(i,cluster,lista);
                      clusters.get(j-1).add_robot(robot);
                      
                      //Con questa mette esattamente 900 robot per cluster
                      if(!(j<100)){
                      j=0;
                      }
                     
                        //setta request con l'oggetto robot
                     String  sql2 = "insert into robot VALUES (?, ?, ?)";
                     PreparedStatement prepareds = con.prepareStatement(sql2);
                     prepareds.setString (1, (String) robot.getIDCLUSTER());
                     prepareds.setInt (2, robot.getIDROBOT());
                     prepareds.setInt (3, 100);
                     prepareds.execute();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(Creazione_Invio.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }     
                    String y="update access SET valore=1";
                    PreparedStatement ss=con.prepareStatement(y);
                    ss.execute();
              }
              //Chiusura della connessione con il DB
              con.close();
          }
          Listner.bo();
                 } catch (SQLException ex) {
          Logger.getLogger(Creazione_Invio.class.getName()).log(Level.SEVERE, null, ex);
      }
}
  

@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
     /* try {
          action();
      } catch (SchedulerException ex) {
          Logger.getLogger(Creazione_Invio.class.getName()).log(Level.SEVERE, null, ex);
      }*/
 }
    
  @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, java.io.IOException{
       try {
           action(request,response);
       } catch (SchedulerException ex) {
           Logger.getLogger(Creazione_Invio.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
  @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        
    }
}