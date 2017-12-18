
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AD-TEAM
 */
public class Robot {
    private final int id_robot;
    private final String id_cluster;
    private ArrayList<Sensore> sensors;
    private int IR;

    //Costruttore
    public Robot(int id_robot, String id_cluster,ArrayList<Sensore> s) {
        this.id_robot=id_robot;
        this.id_cluster=id_cluster;
        this.sensors= s;
        this.IR=100;
    }

    public void change_state(Sensore s, int value){
        for(Sensore cur: sensors){
            if(cur.equals(s))
                cur=s;
        }
    }

    public String info(){
        return this.getIDROBOT()+"<br>"+this.getIDCLUSTER()+"<br>"+this.getSensori();

    }

    public int getIDROBOT (){
        return this.id_robot;
    }

    public String getIDCLUSTER(){
      return this.id_cluster;
    }

    public ArrayList<Sensore> getSensori(){
      return this.sensors;
    }
    public int getIR(){
        return this.IR;
    }
    
    @Override
    public String toString(){
        return "IDROBOT: "+this.getIDROBOT()+"<br>"+this.getSensori()+"<br>";
    }

}
