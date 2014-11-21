/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import Model.Process;
import Model.CPU;
import java.util.Queue;

/**
 *
 * @author rodrigo
 */
public class Scalonator implements Runnable {
    
    protected Queue<Process> ready;
    protected final CPU cpu;

    public Scalonator(CPU cpu) {
        this.cpu = cpu;
    }
    
    public void addProccess(Process p) throws InterruptedException {
        ready.add(p);
        cpu.busy.acquire();
    }
    
    public Process nextProcess() {
        if(ready.isEmpty())
            return null;
        return ready.remove();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Process p = nextProcess();
                if(p != null) {

                    cpu.execute(p);
                    cpu.busy.acquire();
                    if(p.getProccessorTime()>0)
                        addProccess(p);
                }
            } catch(InterruptedException e) {

            }
        }
    }
}
