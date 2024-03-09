package org.tpIliesOana.presentation.Product;

import org.tpIliesOana.bll.ProductBLL;
import org.tpIliesOana.model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the product addition form.
 */
public class ProductAdd implements ActionListener {
    private JPanel panelPanel = new JPanel();
    private JFrame frame = new JFrame("Product Add");
    private JTextField Name = new JTextField();
    private JTextField Price = new JTextField();
    private JTextField Stock = new JTextField();

    private JTextField Detalii =new JTextField();
    private JButton buttonAdaugare = new JButton("Adauga");
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
     * Constructs a new instance of the ProductAdd class.
     */
    public ProductAdd() {
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panelPanel, timesNewRomanFont);
        JLabel labelTitle = new JLabel("Introduceti noul produs:");
        JLabel labelName = new JLabel("Nume:");
        JLabel labelPrice = new JLabel("Pret:");
        JLabel labelStock = new JLabel("Stoc:");
        JLabel labelDetalii= new JLabel("Detalii:");

        buttonAdaugare.setBackground(Color.BLUE);
        buttonAdaugare.setPreferredSize(new Dimension(100, 20));
        panelPanel.setLayout(new GridLayout(12, 2));
        panelPanel.add(labelTitle);
        panelPanel.add(labelName);
        panelPanel.add(Name);
        panelPanel.add(labelPrice);
        panelPanel.add(Price);
        panelPanel.add(labelStock);
        panelPanel.add(Stock);

        panelPanel.add(labelDetalii);
        panelPanel.add(Detalii);
        panelPanel.add(buttonAdaugare);


        buttonAdaugare.addActionListener(this);
        buttonAdaugare.setContentAreaFilled(false);
        buttonAdaugare.setFocusPainted(false);
        panelPanel.setBackground(Color.GREEN);

        frame.setLayout(new BorderLayout());
        frame.add(panelPanel, BorderLayout.CENTER);
        frame.add(buttonAdaugare, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelPanel.setLayout(null);
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
        if (e.getSource() == buttonAdaugare) {
            if (Price.getText().equals("") || Name.getText().equals("") || Stock.getText().equals("")) {
                JOptionPane.showMessageDialog(buttonAdaugare, "Completati toate campurile");
            } else if (Integer.parseInt(Price.getText()) <= 0) {
                JOptionPane.showMessageDialog(buttonAdaugare, "Pretul produsului nu poate fi negativ");
            } else if (Integer.parseInt(Stock.getText()) <= 0) {
                JOptionPane.showMessageDialog(buttonAdaugare, "Stocul produsului trebuie sa fie mai mare >0");
            } else {
                JOptionPane.showMessageDialog(buttonAdaugare, "Produsul a fost adaugat");
                Products product = new Products(Name.getText(), Integer.parseInt(Price.getText()), Integer.parseInt(Stock.getText()), Detalii.getText());
                ProductBLL productBLL = new ProductBLL();
                productBLL.insertProduct(product);
                frame.dispose();
            }
        }
    }
}
