/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import Model.Process;

/**
 *
 * @author rodrigo
 */
public class ProcTableModel extends AbstractTableModel {
    
    private ArrayList<Process> procs;

    public ProcTableModel(ArrayList<Process> procs) {
        this.procs = procs;
    }

    @Override
    public int getRowCount() {
        return procs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "ID";
            case 1:
                return "Tempo de Chegada";
            case 2:
                return "Tempo de Processamento";
        }
        throw new ArrayIndexOutOfBoundsException(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Process p = procs.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getArrivalTime();
            case 2:
                return p.getProccessorTime();
        }
        throw new ArrayIndexOutOfBoundsException(columnIndex);
    }

    public ArrayList<Process> getProcs() {
        return procs;
    }
    
    
    
}
