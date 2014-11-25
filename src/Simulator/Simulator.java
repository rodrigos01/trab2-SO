/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import Model.Device;
import Model.Process;
import View.TelaResumo;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class Simulator implements Runnable {
    
    private ArrayList<Device> devices;
    private ArrayList<Process> procs;
    
    private Scalonator scalonator;
    private CPU cpu;
    
    public static int tempo;

    public Simulator(ArrayList<Device> devices, ArrayList<Process> procs, Scalonator scalonator) {
        this.devices = devices;
        this.procs = procs;
        this.scalonator = scalonator;
        this.cpu = scalonator.getCpu();
    }
    
    public ArrayList<Process> getProcessesByArrivalTime(int time) {
        
        ArrayList<Process> match = new ArrayList<>();
        
        for(Process p: procs) {
            if(p.getArrivalTime() == time) {
                match.add(p);
            }
        }
        
        return match;
    }
    
    public boolean hasIncompleteProcesses() {
        for(Process p: procs) {
            if(p.getState() != Process.State.DONE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        for(tempo = 1; hasIncompleteProcesses(); tempo++) {
            
            for(Process p: getProcessesByArrivalTime(tempo)){
                scalonator.addProccess(p);
            }
            scalonator.tick();
            cpu.tick();
            for(Device d: devices) {
                d.tick();
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        showResumeWindow();
    }
    
    private void showResumeWindow() {

        int tt = tempo;

        String resume = "Resumo da Simulação:\n\n";

        resume += "Processos: "+procs.size()+"\n";
        resume += "Dispositivos: "+devices.size()+"\n\n";

        resume += "Resumo por Processo: \n\n";

        for(Process p: procs) {
            resume += "Processo "+p.getId()+":\n";
            resume += "     tempo de chegada: "+p.getArrivalTime()+"\n";
            resume += "     tempo de processamento: "+p.rt+"\n";
            resume += "     Operações IO: "+p.IOOperationsCount+"\n";
            resume += "     Tempo em espera: "+p.wt+"\n";
            resume += "     Tempo em bloqueado: "+p.bt+"\n";
            resume += "     Tempo Total: "+p.tt+"\n";
        }
        resume += "\n\nResumo por Dispositivo \n\n";
        for(Device d: devices) {
            resume += d.getName()+":\n";
            resume += "     Tempo de operação: "+d.getOperationTime()+"\n";
            resume += "     Tempo total de ocupação: "+d.usedTime+"\n";
            resume += "     Porcentagem de ocupação: "+((d.usedTime*100)/tt)+'%'+"\n";
        }
        resume += "\n\nResumo da CPU \n\n";
        int ut = tt-cpu.idleTime;
        resume += "     Tempo total ocioso: "+cpu.idleTime+"\n";
        resume += "     Tempo total em uso: "+ut+"\n";
        resume += "     Ocupação de CPU: "+((ut*100)/tt)+'%'+"\n";
        resume += "     CPU ociosa: "+((cpu.idleTime*100)/tt)+'%'+"\n";

        new TelaResumo(resume).setVisible(true);
    }
    
}
