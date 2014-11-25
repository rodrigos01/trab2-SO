/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import java.util.LinkedList;

/**
 *
 * @author rodrigo
 */
public class RoundRobinScalonator extends Scalonator {

    private final int quantum = 4;
    private int tempo;
    
    public RoundRobinScalonator(CPU cpu) {
        super(cpu);
        tempo = 0;
        ready = new LinkedList<>();
    }

    @Override
    public void tick() {
        if(tempo == quantum) {
            scalonate();
            tempo = 0;
        } else {
            tempo++;
        }
    }
    
}
