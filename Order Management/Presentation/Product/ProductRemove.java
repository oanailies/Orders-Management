package org.tpIliesOana.presentation.Product;

import org.tpIliesOana.bll.ProductBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the product removal form.
 */
public class ProductRemove implements ActionListener {
    private JFrame frame = new JFrame("Remove produs");
    private JPanel panelProduct = new JPanel();
    private JLabel labelTitle = new JLabel("Introduceti un ID valid de produs!");
    private JTextField ID = new JTextField();
    private JButton buttonDelete = new JButton("Sterge");

    /**
     * Constructs a new instance of the ProductRemove class.
     */
    public ProductRemove() {
        labelTitle.setFont(new Font("Times New Roman", Font.BOLD, 14));
        buttonDelete.setFocusPainted(false);
        buttonDelete.setContentAreaFilled(false);

        panelProduct.setLayout(new GridLayout(12, 2));
        panelProduct.add(labelTitle);
        panelProduct.add(ID);
        panelProduct.add(buttonDelete);

        panelProduct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonDelete.addActionListener(this);
        buttonDelete.setPreferredSize(new Dimension(100, 20));
        panelProduct.setBackground(Color.GREEN);
        frame.setLayout(new BorderLayout());
        frame.add(panelProduct, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelProduct.setLayout(null);
        frame.setSize(350, 150);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Handles the action performed event.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDelete) {
            if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog(buttonDelete, "Introduceti un ID valid!");
            } else {
                ProductBLL productBLL = new ProductBLL();
                productBLL.deleteProduct(Integer.parseInt(ID.getText()));
                JOptionPane.showMessageDialog(buttonDelete, "Produsul a fost sters!");
                frame.dispose();
            }
        }
    }
}
