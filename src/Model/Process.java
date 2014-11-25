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
public class Process extends Observable implements Comparable<Process>{
    
    private int id, proccessorTime, arrivalTime;
    private LinkedList<Device> IOOperations;
    private static int idCount = 0;
    
    public int tt = 0, rt = 0, wt = 0, bt = 0, ttc = 0, rtc =0, wtc = 0, btc = 0, IOOperationsCount = 0;
    
    public enum State {
        BLOCKED, READY, EXECUTING, DONE
    }
    
    private State state;
    private State previousState;

    public Process(int proccessorTime, int arrivalTime) {
        this.proccessorTime = proccessorTime;
        rt = proccessorTime;
        this.arrivalTime = arrivalTime;
        this.id = ++idCount;
        this.IOOperations = new LinkedList<>();
        this.state = State.READY;
    }

    public int getId() {
        return id;
    }

    public int getProccessorTime() {
        return proccessorTime;
    }

    public void setProccessorTime(int proccessorTime) {
        this.proccessorTime = proccessorTime;
        setChanged();
        notifyObservers(proccessorTime);
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LinkedList<Device> getIOOperations() {
        return IOOperations;
    }

    public State getState() {
        return state;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setState(State state) {
        this.previousState = this.state;
        this.state = state;
        setChanged();
        notifyObservers(state);
    }

    public void addIOOperation(Device dev) {
        this.IOOperations.add(dev);
        this.IOOperationsCount++;
    }

    @Override
    public String toString() {
        return ""+id;
    }

    @Override
    public int compareTo(Process t) {
        return this.proccessorTime - t.proccessorTime;
    }
    
    
    public void doIOOperation() {
        if(!IOOperations.isEmpty()){
            IOOperations.remove().block(this);
        }
    }
    
    
    
}
