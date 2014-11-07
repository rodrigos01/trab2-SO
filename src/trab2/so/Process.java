/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab2.so;

import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class Process implements Comparable<Process>{
    
    private int id, proccessorTime, arrivalTime;
    private ArrayList<Device> IOOperations;
    private static int idCount = 0;

    public Process(int proccessorTime, int arrivalTime) {
        this.proccessorTime = proccessorTime;
        this.arrivalTime = arrivalTime;
        this.id = ++idCount;
        this.IOOperations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getProccessorTime() {
        return proccessorTime;
    }

    public void setProccessorTime(int proccessorTime) {
        this.proccessorTime = proccessorTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<Device> getIOOperations() {
        return IOOperations;
    }

    public void addIOOperation(Device dev) {
        this.IOOperations.add(dev);
    }

    @Override
    public int compareTo(Process t) {
        return this.proccessorTime - t.proccessorTime;
    }
    
    
    
    
    
}
