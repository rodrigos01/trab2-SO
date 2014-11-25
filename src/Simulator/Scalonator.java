/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import Model.Process;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public abstract class Scalonator implements Observer {
    
    protected Queue<Process> ready;
    protected CPU cpu;

    public Scalonator(CPU cpu) {
        this.cpu = cpu;
    }

    public CPU getCpu() {
        return cpu;
    }
    
    public void addProccess(Process p) {
        ready.add(p);
        p.addObserver(this);
    }
    
    public Process nextProcess() {
        if(ready.isEmpty())
            return null;
        return ready.remove();
    }
    
    protected void scalonate() {
        if(!ready.isEmpty()) {
            if(!cpu.isIdle())
                ready.add(cpu.getProccess());
            cpu.setProccess(ready.remove());
        }
    }
    
    /**
     *
     * @param obj
     * @param arg
     */
    @Override
    public void update(Observable obj, Object arg){
        Process p = (Process) obj;
        if(arg instanceof Process.State) {
            Process.State s = p.getPreviousState();
            if(s == Process.State.READY) {
                ready.add(p);
                p.wt += Simulator.tempo-p.wtc;
            } else if(s == Process.State.BLOCKED) {
                p.bt += Simulator.tempo-p.btc;
            }
            if(arg == Process.State.READY) {
                ready.add(p);
                p.wtc= Simulator.tempo;
            } else if(arg == Process.State.BLOCKED) {
                p.btc = Simulator.tempo;
            } else if(arg == Process.State.DONE) {
                p.tt = p.rt+p.bt+p.wt;
            }
        }
    }
    
    public abstract void tick();
}
