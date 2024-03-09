package org.tpIliesOana.presentation.Product;

import org.tpIliesOana.bll.ProductBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Represents a table for displaying products.
 */

public class ProductTable {
    private JPanel panelProduct = new JPanel();
    private JFrame frame = new JFrame("Products");
    private JTable table=new JTable();

    /**
     * Gets the table instance.
     *
     * @return The JTable instance.
     */
    public JTable getTable()
    {
        return table;
    }


    /**
     * Constructs a new instance of the ProductTable class.
     *
     * @param showFrame Indicates whether to display the table in a JFrame or not.
     */
    public ProductTable(boolean showFrame) {
        ProductBLL productBLL = new ProductBLL();

        DefaultTableModel defaultTableModel = new DefaultTableModel(productBLL.getListOfProducts(), productBLL.getFieldNames()){
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
     * Displays the table in a JFrame.
     */
    private void show()
    {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(400);
        columnModel.getColumn(4).setPreferredWidth(500);
        panelProduct.add(new JScrollPane(table));
        panelProduct.setBackground(Color.GREEN);
        frame.setContentPane(panelProduct);
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
