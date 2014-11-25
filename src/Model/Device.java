/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
import java.util.Observable;

/**
 *
 * @author rodrigo
 */
public class Device extends Observable {
    
    private static int idCount = 0;
    
    private int id, operationTime, time;
    private String name;
    private LinkedList<Process> blocked;
    private Process using;
    
    public int usedTime;

    public Device(int operationTime, String name) {
        this.operationTime = operationTime;
        this.time = 0;
        this.name = name;
        
        blocked = new LinkedList<>();
        
        this.id = ++idCount;
    }

    public int getId() {
        return id;
    }

    public int getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(int operationTime) {
        this.operationTime = operationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Process> getBlocked() {
        return blocked;
    }

    public void setBlocked(LinkedList<Process> blocked) {
        this.blocked = blocked;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public void block(Process p) {
        blocked.add(p);
        setChanged();
        notifyObservers(blocked);
        p.setState(Process.State.BLOCKED);
    }
    
    public void tick() {
        if(using == null) {
            if(!blocked.isEmpty()) {
                using = blocked.remove();
                setChanged();
                notifyObservers(blocked);
                notifyObservers(using);
            }
        } else {
            if(time >= operationTime) {
                time = 0;
                using.setState(Process.State.READY);
                using = null;
            } else {
                time++;
                usedTime++;
                setChanged();
                notifyObservers(operationTime-time);
            }
        }
    }
    
    
    
}
