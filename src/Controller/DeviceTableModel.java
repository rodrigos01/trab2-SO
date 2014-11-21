/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Device;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigo
 */
public class DeviceTableModel extends AbstractTableModel {
    
    private final ArrayList<Device> devices;

    public DeviceTableModel(ArrayList<Device> devices) {
        this.devices = devices;
    }
    
    @Override
    public int getRowCount() {
        return devices.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Nome";
            case 1:
                return "Tempo de Operação";
        }
        throw new ArrayIndexOutOfBoundsException(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Device d = devices.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return d.getName();
            case 1:
                return d.getOperationTime();
        }
        throw new ArrayIndexOutOfBoundsException(columnIndex);
    }
    
}
