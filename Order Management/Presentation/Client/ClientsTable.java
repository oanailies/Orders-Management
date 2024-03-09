package org.tpIliesOana.presentation.Client;

import org.tpIliesOana.bll.ClientBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
/**
 * The ClientsTable class is responsible for displaying a table of clients in a graphical user interface (GUI).
 */
public class ClientsTable {
    private JPanel panelClient = new JPanel();
    private JFrame frame = new JFrame("Clients");
    private JTable table=new JTable();

    /**
     * Retrieves the JTable component containing the clients' data.
     *
     * @return The JTable component.
     */

    public JTable getTable()
    {
        return table;
    }

    /**
     * Constructs a ClientsTable object and initializes the GUI components.
     *
     * @param showFrame Indicates whether to show the frame containing the clients' table.
     */
    public ClientsTable(boolean showFrame) {
        ClientBLL clientBLL = new ClientBLL();

        DefaultTableModel defaultTableModel = new DefaultTableModel(clientBLL.getListOfClients(), clientBLL.getFieldNames()){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(defaultTableModel);

        table.getTableHeader().setReorderingAllowed(false);
        if(showFrame){
            show();}
    }
    private void show()
    {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(400);
        columnModel.getColumn(4).setPreferredWidth(500);
        panelClient.add(new JScrollPane(table));
        panelClient.setBackground(Color.GREEN);
        frame.setContentPane(panelClient);
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
