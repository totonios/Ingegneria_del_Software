
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AD-TEAM
 */
public class Cluster {
    String ID;
    ArrayList<Robot> robots;
    int IR;
    
    //Costruttore
    public Cluster(String id_cl,int ir){
        this.ID=id_cl;
        this.IR=ir;
        this.robots=new ArrayList<>();
    }
    
    public void add_robot(Robot r){
        this.robots.add(r);
    }
    
    public ArrayList<Robot> getRobots(){
        return this.robots;
    }
    public String getIDCLUSTER(){
        return this.ID;
    }
    public String infoc(){
        return "IDCLUSTER: "+this.getIDCLUSTER()+"<br>"+this.getRobots();
    }
    public int getIR(){
        return this.IR;
    }
    
}
