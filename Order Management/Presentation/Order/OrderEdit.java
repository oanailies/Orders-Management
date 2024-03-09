package org.tpIliesOana.presentation.Order;

import org.tpIliesOana.bll.OrdersBLL;
import org.tpIliesOana.bll.ProductBLL;
import org.tpIliesOana.model.Orders;
import org.tpIliesOana.model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a graphical user interface for editing orders.
 */
public class OrderEdit  implements ActionListener {

    private JFrame frame = new JFrame("Ordert Edit");
    private JPanel panelOrder = new JPanel();
    private JTextField textSearch = new JTextField();
    private JTextField IDClient = new JTextField();
    private JTextField IDProduct = new JTextField();
    private JTextField ODate = new JTextField();
    private JTextField quantity = new JTextField();
    private JButton buttonSearch = new JButton("Cauta");
    private JButton buttonEdit = new JButton("Modifica");

    /**
     * Sets the font for all components within the container and its nested containers.
     *
     * @param container The container whose components' font will be set.
     * @param font      The font to be set.
     */
    private void setPanelFont(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                setPanelFont((Container) component, font);
            }
            component.setFont(font);
        }
    }

    /**
     * Constructs a new instance of the OrderEdit class.
     */
    public OrderEdit() {

        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panelOrder, timesNewRomanFont);
        JLabel labelTitle = new JLabel("Datele comenzii ");
        JLabel labelClient = new JLabel("ID Client ");
        JLabel labelProdus = new JLabel("ID Product ");
        JLabel labelODate = new JLabel("Data ");
        JLabel labelQuanity = new JLabel("Quantity");
        JLabel labelSearch = new JLabel("Introduceti ID-ul comenzii-ului");


        buttonEdit.setPreferredSize(new Dimension(100, 20));
        panelOrder.setLayout(new GridLayout(12, 2));
        panelOrder.add(labelTitle);
        panelOrder.add(new JLabel(""));
        panelOrder.add(labelSearch);
        panelOrder.add(textSearch);
        panelOrder.add(buttonSearch);
        panelOrder.add(new JLabel(""));
        panelOrder.add(labelClient);
        panelOrder.add(IDClient);
        panelOrder.add(labelProdus);
        panelOrder.add(IDProduct);
        panelOrder.add(labelODate);
        panelOrder.add(ODate);
        panelOrder.add(labelQuanity);
        panelOrder.add(quantity);
        panelOrder.add(buttonEdit);
        panelOrder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonEdit.addActionListener(this);
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setFocusPainted(false);

        buttonSearch.addActionListener(this);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setFocusPainted(false);
        panelOrder.setBackground(Color.GREEN);

        frame.setLayout(new BorderLayout());
        frame.add(panelOrder, BorderLayout.CENTER);
        frame.add(buttonEdit, BorderLayout.EAST);
        frame.add(buttonEdit, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelOrder.setLayout(null);
        frame.setSize(400, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    /**
     * Handles the action performed event for the buttons.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSearch) {
            handleSearchAction();
        }

        if (e.getSource() == buttonEdit) {
            handleEditAction();
        }
    }

    /**
     * Handles the search action.
     * Retrieves the order based on the given order ID and populates the fields with the order details.
     */

    private void handleSearchAction() {
        if (textSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(buttonSearch, "Introduceti ID-ul comenzii");
        } else {
            OrdersBLL ordersBLL = new OrdersBLL();
            Orders o = ordersBLL.findOrderById(Integer.parseInt(textSearch.getText()));
            IDClient.setText(String.valueOf(o.getcID()));
            IDProduct.setText(String.valueOf(o.getpID()));
            ODate.setText(String.valueOf(o.getOdate()));
            quantity.setText(String.valueOf(o.getQuantity()));
        }
    }

    /**
     * Handles the edit action.
     * Updates the order with the modified details.
     */
    private void handleEditAction() {
        if (textSearch.getText().equals("") || IDClient.getText().equals("") || IDProduct.getText().equals("") || ODate.getText().equals("") || quantity.getText().equals("")) {
            JOptionPane.showMessageDialog(buttonEdit, "Completati toate campurile!");
        } else {
            JOptionPane.showMessageDialog(buttonEdit, "Datele produsului au fost modificate");
            ProductBLL productBLL = new ProductBLL();
            Products p = productBLL.findProductById(Integer.parseInt(textSearch.getText()));
            p.setProductName(IDClient.getText());
            p.setDetalii(quantity.getText());
            p.setProductPrice(Integer.parseInt(IDProduct.getText()));
            p.setProductStock(Integer.parseInt(ODate.getText()));

            productBLL.updateProduct(p);
            frame.dispose();
        }
    }

}
