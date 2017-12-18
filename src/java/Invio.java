
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AD-TEAM
 */
public class Invio implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
       Random r=new Random();
        ArrayList<String> lista = new ArrayList<>(100);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Connessione con il DB
        String url = "jdbc:mysql://localhost/mydb";
      
        Connection con;
        try {con = DriverManager.getConnection(url, "root", "root");
          String cl="select idCluster from cluster";
          PreparedStatement s=con.prepareStatement(cl);
          ResultSet rs =s.executeQuery();
          while (rs.next()) {
              lista.add(rs.getNString(1));
          }
          //Invia un numero di pacchetti >0 e <=90000 al minuto
          String sql="insert into calcolo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          PreparedStatement preparedStmt = con.prepareStatement(sql);
          for(int i=0;i<r.nextInt(90001);i++){
            preparedStmt.setNull(3,java.sql.Types.INTEGER);
            preparedStmt.setNull(4,java.sql.Types.INTEGER);
            preparedStmt.setNull(5,java.sql.Types.INTEGER);
            preparedStmt.setNull(6,java.sql.Types.INTEGER);
            preparedStmt.setNull(7,java.sql.Types.INTEGER);
            preparedStmt.setNull(8,java.sql.Types.INTEGER);
            preparedStmt.setNull(9,java.sql.Types.INTEGER);
            preparedStmt.setInt(1,r.nextInt(90001));
            preparedStmt.setString(2,lista.get(r.nextInt(101)));
            preparedStmt.setInt(r.nextInt(6)+3,r.nextInt(2));
            preparedStmt.setTimestamp(10,new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStmt.execute();
        }
          //Chiusura connessione con il DB
          con.close();
          } 
            catch (SQLException ex) {
            Logger.getLogger(Invio.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }
}
    

