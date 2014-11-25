/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import Model.Process;
import java.util.Observable;

/**
 *
 * @author rodrigo
 */
public class CPU extends Observable {
    
    private Process proccess;
    private boolean idle = true;
    
    public int idleTime;

    public Process getProccess() {
        return proccess;
    }

    public void setProccess(Process proccess) {
        this.proccess = proccess;
        this.proccess.setState(Process.State.EXECUTING);
        idle = false;
        setChanged();
        notifyObservers(this.proccess);
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle() {
        this.idle = true;
        this.proccess = null;
    }
    
    public void tick() {
        if(!idle) {
            proccess.setProccessorTime(proccess.getProccessorTime()-1);
            proccess.doIOOperation();
            
            if (proccess.getState() == Process.State.BLOCKED) {
                setIdle();
            } else if(proccess.getProccessorTime()<=0) {
                proccess.setState(Process.State.DONE);
                setIdle();
            } 
        } else {
            idleTime++;
        }
    }
    
}
