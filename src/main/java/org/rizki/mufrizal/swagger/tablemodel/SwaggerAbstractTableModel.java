package org.rizki.mufrizal.swagger.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.rizki.mufrizal.swagger.client.object.ProxyResponse;

/**
 *
 * @author rizki
 */
public class SwaggerAbstractTableModel extends AbstractTableModel {

    private List<ProxyResponse> proxyResponses = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "Name",
        "State",
        "Version",
        "Message"
    };

    public SwaggerAbstractTableModel(List<ProxyResponse> proxyResponses) {
        this.proxyResponses = proxyResponses;
    }

    @Override
    public int getRowCount() {
        return this.proxyResponses.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProxyResponse proxyResponse = proxyResponses.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                rowIndex + 1;
            case 1 ->
                proxyResponse.getName();
            case 2 ->
                proxyResponse.getState();
            case 3 ->
                proxyResponse.getVersion();
            case 4 ->
                proxyResponse.getMessage();
            default ->
                null;
        };
    }

}
