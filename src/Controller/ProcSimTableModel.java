/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import Model.Process;

/**
 *
 * @author rodrigo
 */
public class ProcSimTableModel extends AbstractTableModel implements Observer {

    private ArrayList<Process> procs;
    
    public ProcSimTableModel(ArrayList<Process> procs) {
        this.procs = procs;
        for(Process p: this.procs) {
            p.addObserver(this);
        }
    }
    
    @Override
    public int getRowCount() {
        return procs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "ID";
            case 1:
                return "Tempo de Chegada";
            case 2:
                return "Estado";
            case 3:
                return "Tempo de Processamento";
        }
        throw new ArrayIndexOutOfBoundsException(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Process p = procs.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getArrivalTime();
            case 2:
                if(p.getState() == Model.Process.State.READY)
                    return "PRONTO";
                else if(p.getState() == Model.Process.State.EXECUTING)
                    return "EXECUTANDO";
                else if(p.getState() == Model.Process.State.DONE)
                    return "FINALIZADO";
                else if(p.getState() == Model.Process.State.BLOCKED)
                    return "BLOQUEADO";
            case 3:
                return p.getProccessorTime();
        }
        throw new ArrayIndexOutOfBoundsException(columnIndex);
    }

    public ArrayList<Model.Process> getProcs() {
        return procs;
    }

    @Override
    public void update(Observable o, Object arg) {
        fireTableDataChanged();
    }
    
}
