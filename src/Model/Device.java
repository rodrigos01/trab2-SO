/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author rodrigo
 */
public class Device {
    
    private static int idCount = 0;
    
    private int id, operationTime;
    private String name;

    public Device(int operationTime, String name) {
        this.operationTime = operationTime;
        this.name = name;
        
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
    
    
    
    
    
}
