package org.tpIliesOana.presentation.Product;

import org.tpIliesOana.bll.ProductBLL;
import org.tpIliesOana.model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the product editing form.
 */
public class ProductEdit implements ActionListener {

    private JPanel panelProdus = new JPanel();
    private JFrame frame = new JFrame("Product Edit");
    private JTextField textSearch = new JTextField();
    private JTextField Name = new JTextField();
    private JTextField Price = new JTextField();
    private JTextField Stock = new JTextField();
    private JTextField Detalii = new JTextField();
    private JButton buttonSearch = new JButton("Cauta");
    private JButton buttonEdit = new JButton("Modifica");


    /**
     * Sets the font for all components within the given container.
     *
     * @param container The container to set the font for.
     * @param font      The font to set.
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
     * Constructs a new instance of the ProductEdit class.
     */
    public ProductEdit() {

        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panelProdus, timesNewRomanFont);
        JLabel labelTitle = new JLabel("Datele clientului ");
        JLabel labelName = new JLabel("Nume:");
        JLabel labelPret = new JLabel("Pret:");
        JLabel labelStoc = new JLabel("Stoc:");
        JLabel labelDetalii = new JLabel("Detalii:");
        JLabel labelSearch = new JLabel("Introduceti ID-ul produs-ului");

        buttonEdit.setPreferredSize(new Dimension(100, 20));
        panelProdus.setLayout(new GridLayout(12, 2));
        panelProdus.add(labelTitle);
        panelProdus.add(new JLabel(""));
        panelProdus.add(labelSearch);
        panelProdus.add(textSearch);
        panelProdus.add(buttonSearch);
        panelProdus.add(new JLabel(""));
        panelProdus.add(labelName);
        panelProdus.add(Name);
        panelProdus.add(labelPret);
        panelProdus.add(Price);
        panelProdus.add(labelStoc);
        panelProdus.add(Stock);
        panelProdus.add(labelDetalii);
        panelProdus.add(Detalii);
        panelProdus.add(buttonEdit);
        panelProdus.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonEdit.addActionListener(this);
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setFocusPainted(false);

        buttonSearch.addActionListener(this);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setFocusPainted(false);
        panelProdus.setBackground(Color.GREEN);

        frame.setLayout(new BorderLayout());
        frame.add(panelProdus, BorderLayout.CENTER);
        frame.add(buttonEdit, BorderLayout.EAST);
        frame.add(buttonEdit, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelProdus.setLayout(null);
        frame.setSize(400, 350);
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
        if (e.getSource() == buttonSearch) {
            if (textSearch.getText().equals("")) {
                JOptionPane.showMessageDialog(buttonSearch, "Introduceti ID-ul produsului");
            } else {
                ProductBLL productBLL = new ProductBLL();
                Products p = productBLL.findProductById(Integer.parseInt(textSearch.getText()));
                Name.setText(p.getProductName());
                Price.setText(String.valueOf(p.getProductPrice()));
                Stock.setText(String.valueOf(p.getProductStock()));
                Detalii.setText(String.valueOf(p.getDetalii()));
            }
        }

        if (e.getSource() == buttonEdit) {
            if (textSearch.getText().equals("") || Name.getText().equals("") || Price.getText().equals("") || Stock.getText().equals("") || Detalii.getText().equals("")) {
                JOptionPane.showMessageDialog(buttonEdit, "Completati toate campurile!");
            } else if (Integer.parseInt(Price.getText()) <= 0) {
                JOptionPane.showMessageDialog(buttonEdit, "Pretul produsului nu poate fi negativ");
            } else if (Integer.parseInt(Stock.getText()) <= 0) {
                JOptionPane.showMessageDialog(buttonEdit, "Stocul produsului trebuie sa fie mai mare >0");
            } else {
                JOptionPane.showMessageDialog(buttonEdit, "Datele produsului au fost modificate");
                ProductBLL productBLL = new ProductBLL();
                Products p = productBLL.findProductById(Integer.parseInt(textSearch.getText()));
                p.setProductName(Name.getText());
                p.setDetalii(Detalii.getText());
                p.setProductPrice(Integer.parseInt(Price.getText()));
                p.setProductStock(Integer.parseInt(Stock.getText()));

                productBLL.updateProduct(p);
                frame.dispose();
            }
        }
    }

}
