/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab2.so;

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
