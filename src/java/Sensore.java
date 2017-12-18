/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alfredo
 */
public class Sensore {
    private boolean state;
    
    //Costruttore
    public Sensore(){
        this.state=true;
    }

    public void change(boolean value){
        this.state=value;
    }
    
    public int getState(){
        if(this.state){
            return 1;
        }else return 0;
    }
            
    @Override
    public String toString(){
        return ""+this.state;
    }
}
