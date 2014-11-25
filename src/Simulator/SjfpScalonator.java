/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import java.util.PriorityQueue;
import Model.Process;

/**
 *
 * @author rodrigo
 */
public class SjfpScalonator extends Scalonator {

    public SjfpScalonator(CPU cpu) {
        super(cpu);
        ready = new PriorityQueue<Process>();
    }

    @Override
    public void tick() {
        if(ready.isEmpty())
            return;
        Process newProcess = ready.element();
        Process current = cpu.getProccess();
        if(current == null || newProcess.getProccessorTime() < current.getProccessorTime())
            scalonate();
        
    }
    
}
