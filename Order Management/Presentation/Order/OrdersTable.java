package org.tpIliesOana.presentation.Order;

import org.tpIliesOana.bll.OrdersBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Represents a table displaying orders.
 */

public class OrdersTable {
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Orders");
    private JTable table=new JTable();



    /**
     * Constructs a new instance of the OrdersTable class.
     *
     * @param showFrame Determines whether to display the frame or not.
     */
    public OrdersTable(boolean showFrame) {
        OrdersBLL ordersBLL = new OrdersBLL();

        DefaultTableModel defaultTableModel = new DefaultTableModel(ordersBLL.getListOfOrders(), ordersBLL.getFieldNames()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(defaultTableModel);

        table.getTableHeader().setReorderingAllowed(false);
        if(showFrame){
            show();}
    }
    /**
     * Displays the frame with the table.
     */
        private void show()
        {
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(200);
            columnModel.getColumn(1).setPreferredWidth(200);
            columnModel.getColumn(2).setPreferredWidth(300);
            columnModel.getColumn(3).setPreferredWidth(400);
            columnModel.getColumn(4).setPreferredWidth(500);
            panel.add(new JScrollPane(table));
            panel.setBackground(Color.GREEN);
            frame.setContentPane(panel);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    /**
     * Retrieves the table component.
     *
     * @return The JTable component representing the orders table.
     */
    public JTable getTable() {
        return table;
    }


}
