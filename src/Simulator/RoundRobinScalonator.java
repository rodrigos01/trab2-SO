/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import Model.CPU;
import java.util.LinkedList;

/**
 *
 * @author rodrigo
 */
public class RoundRobinScalonator extends Scalonator {

    public RoundRobinScalonator(CPU cpu) {
        super(cpu);
        ready = new LinkedList<>();
    }
    
}
