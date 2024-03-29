package org.rizki.mufrizal.swagger.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.rizki.mufrizal.swagger.SwaggerDuplicate;
import org.rizki.mufrizal.swagger.client.ProxyClient;
import org.rizki.mufrizal.swagger.client.object.ProxyResponse;
import org.rizki.mufrizal.swagger.client.rest.ProxyRestClient;
import org.rizki.mufrizal.swagger.helper.GlobalTableCellRenderer;
import org.rizki.mufrizal.swagger.tablemodel.SwaggerAbstractTableModel;

/**
 *
 * @author rizki
 */
public class SwaggerController {

    private ProxyClient proxyClient;
    private SwaggerAbstractTableModel swaggerAbstractTableModel;
    private final SwaggerDuplicate swaggerDuplicate;
    private GlobalTableCellRenderer globalTableCellRenderer;
    List<ProxyResponse> dataProxies = new ArrayList<>();

    public SwaggerController(SwaggerDuplicate swaggerDuplicate) {
        this.swaggerDuplicate = swaggerDuplicate;
    }

    public void init() {
        this.swaggerAbstractTableModel = new SwaggerAbstractTableModel(dataProxies);
        this.swaggerDuplicate.getTable().setModel(swaggerAbstractTableModel);
        this.autoResizetable();

        this.swaggerDuplicate.getProgressBar().setVisible(Boolean.FALSE);

        this.proxyClient = new ProxyRestClient();
    }

    public void showDuplicateSwagger() {
        List<ProxyResponse> proxyResponses = this.proxyClient.getAllProxies("", "", "", "");

        List<ProxyResponse> noDuplicate = new ArrayList<>();
        List<ProxyResponse> duplicate = new ArrayList<>();

        proxyResponses.forEach(p -> {
            Optional<ProxyResponse> o = noDuplicate.stream().filter(n -> n.getPath().equals(p.getPath())).findAny();
            if (o.isPresent()) {
                if (p.getDeprecated() == true) {
                    duplicate.add(p);
                } else {
                    duplicate.add(o.get());
                }

                if (p.getRetired() == true) {
                    duplicate.add(p);
                } else {
                    duplicate.add(o.get());
                }

                if (p.getExpired() == true) {
                    duplicate.add(p);
                } else {
                    duplicate.add(o.get());
                }

                if (p.getState().equals("unpublished")) {
                    duplicate.add(p);
                } else {
                    duplicate.add(o.get());
                }
            } else {
                noDuplicate.add(p);
            }
        });

        this.swaggerAbstractTableModel = new SwaggerAbstractTableModel(duplicate);
        this.swaggerDuplicate.getTable().setModel(this.swaggerAbstractTableModel);
        this.autoResizetable();
    }

    /**
     * table *
     */
    private void autoResizetable() {
        for (int i = 0; i < this.swaggerDuplicate.getTable().getColumnCount(); i++) {
            setColumnWidthtable(i);
        }
    }

    private void setColumnWidthtable(int kolom) {
        DefaultTableColumnModel modelKolom = (DefaultTableColumnModel) this.swaggerDuplicate.getTable().getColumnModel();
        TableColumn kolomTabel = modelKolom.getColumn(kolom);
        int lebar = 0;
        int margin = 10;
        int a;

        TableCellRenderer renderer = kolomTabel.getHeaderRenderer();
        if (renderer == null) {
            renderer = this.swaggerDuplicate.getTable().getTableHeader().getDefaultRenderer();
        }
        Component komponen = renderer.getTableCellRendererComponent(this.swaggerDuplicate.getTable(), kolomTabel.getHeaderValue(), false, false, 0, 0);
        lebar = komponen.getPreferredSize().width;
        for (a = 0; a < this.swaggerDuplicate.getTable().getRowCount(); a++) {
            renderer = this.swaggerDuplicate.getTable().getCellRenderer(a, kolom);
            komponen = renderer.getTableCellRendererComponent(this.swaggerDuplicate.getTable(), this.swaggerDuplicate.getTable().getValueAt(a, kolom), false, false, a, kolom);
            int lebarKolom = komponen.getPreferredSize().width;
            lebar = Math.max(lebar, lebarKolom);

        }
        lebar = lebar + margin;
        kolomTabel.setPreferredWidth(lebar);
    }
    /**
     * table *
     */
}
