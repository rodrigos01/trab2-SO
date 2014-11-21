/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.concurrent.Semaphore;

/**
 *
 * @author rodrigo
 */
public class CPU implements Runnable {
    
    private final Semaphore idle;
    public Semaphore busy;
    private Process running;

    public CPU() {
        idle = new Semaphore(0);
        busy = new Semaphore(1);
    }    
   
    public void execute(Process p) {
        running = p;
        idle.release();
    }

    @Override
    public void run() {
        try {
            while(true) {
                idle.acquire();
                while(running.getProccessorTime()>0) {
                    Thread.sleep(1000);
                    running.setProccessorTime(running.getProccessorTime() - 1);
                }
                busy.release();
            }
        } catch (InterruptedException e) {

        }
    }
    
}
