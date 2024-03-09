package org.tpIliesOana.presentation;

import org.tpIliesOana.presentation.Client.ClientAdd;
import org.tpIliesOana.presentation.Client.ClientEdit;
import org.tpIliesOana.presentation.Client.ClientRemove;
import org.tpIliesOana.presentation.Client.ClientsTable;
import org.tpIliesOana.presentation.Order.OrderAdd;
import org.tpIliesOana.presentation.Order.OrderEdit;
import org.tpIliesOana.presentation.Order.OrderRemove;
import org.tpIliesOana.presentation.Order.OrdersTable;
import org.tpIliesOana.presentation.Product.ProductAdd;
import org.tpIliesOana.presentation.Product.ProductEdit;
import org.tpIliesOana.presentation.Product.ProductRemove;
import org.tpIliesOana.presentation.Product.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The View class represents the graphical user interface (GUI) of the application.
 * It provides a visual representation of the client, product, and order management system.
 * The GUI consists of various buttons and panels for interacting with the system.
 * The class implements the ActionListener interface to handle user actions.
 */
public class View implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel Clients = new JLabel("Clients");
    private JLabel Products = new JLabel("Products");
    private JLabel Orders = new JLabel("Orders");

    private JButton btnOpenClients=new JButton("Clients");
    private JButton buttonAddC = new JButton("Add");
    private JButton buttonEditC = new JButton("Edit");
    private JButton buttonDeleteC = new JButton("Delete");
    private JButton buttonViewC = new JButton("Clients");

    private JButton buttonAddP = new JButton("Add");
    private JButton buttonEditP = new JButton("Edit");
    private JButton buttonDelP = new JButton("Delete");
    private JButton buttonViewP = new JButton("Products");
    private JButton buttonNewOrder = new JButton("Add");
    private JButton buttonViewOrders = new JButton("Orders");

    private JButton buttonEditOrder = new JButton("Edit");
    private JButton buttonDeleteOrder = new JButton("Delete");

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();

    /**
     * Sets the font for all components in the specified container and its sub-containers.
     *
     * @param container the container to set the font for
     * @param font      the font to be set
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
     * Creates an instance of the View class.
     * Initializes the GUI components, sets their properties, and adds them to the frame.
     * Sets the size, location, and visibility of the frame.
     */
    public View() {
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panel1, timesNewRomanFont);
        setPanelFont(panel2, timesNewRomanFont);
        setPanelFont(panel2, timesNewRomanFont);
        panel1.setBackground(Color.GREEN);
        panel2.setBackground(Color.GREEN);
        panel3.setBackground(Color.GREEN);
        panel.setBackground(Color.GREEN);
        frame.setBackground(Color.GREEN);

        panel1.add(Clients);
        panel1.add(buttonAddC);
        panel1.add(buttonEditC);
        panel1.add(buttonDeleteC);
        panel1.add(buttonViewC);

        panel2.add(Products);
        panel2.add(buttonAddP);
        panel2.add(buttonEditP);
        panel2.add(buttonDelP);
        panel2.add(buttonViewP);

        panel3.add(Orders);
        panel3.add(buttonNewOrder);
        panel3.add(buttonEditOrder);
        panel3.add(buttonDeleteOrder);
        panel3.add(buttonViewOrders);


        frame.setLayout(new GridLayout(12, 2));

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel);


        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBackground(Color.GREEN);

        buttonNewOrder.setFocusPainted(false);
        buttonNewOrder.setContentAreaFilled(false);
        buttonViewOrders.setFocusPainted(false);
        buttonViewOrders.setContentAreaFilled(false);

        buttonDeleteOrder.setFocusPainted(false);
        buttonDeleteOrder.setContentAreaFilled(false);

        buttonEditOrder.setFocusPainted(false);
        buttonEditOrder.setContentAreaFilled(false);

        buttonAddC.addActionListener(this);
        buttonEditC.addActionListener(this);
        buttonDeleteC.addActionListener(this);
        buttonViewC.addActionListener(this);
        buttonAddP.addActionListener(this);
        buttonEditP.addActionListener(this);
        buttonDelP.addActionListener(this);
        buttonViewP.addActionListener(this);
        buttonNewOrder.addActionListener(this);
        buttonViewOrders.addActionListener(this);
        buttonEditOrder.addActionListener(this);
        buttonDeleteOrder.addActionListener(this);

        buttonAddC.setFocusPainted(false);
        buttonAddC.setContentAreaFilled(false);
        buttonEditC.setFocusPainted(false);
        buttonEditC.setContentAreaFilled(false);
        buttonDeleteC.setFocusPainted(false);
        buttonDeleteC.setContentAreaFilled(false);
        buttonViewC.setFocusPainted(false);
        buttonViewC.setContentAreaFilled(false);

        buttonAddP.setFocusPainted(false);
        buttonAddP.setContentAreaFilled(false);
        buttonEditP.setFocusPainted(false);
        buttonEditP.setContentAreaFilled(false);
        buttonDelP.setFocusPainted(false);
        buttonDelP.setContentAreaFilled(false);
        buttonViewP.setFocusPainted(false);
        buttonViewP.setContentAreaFilled(false);



    }

    /**
     * Called when an action event occurs.
     * Handles the action events generated by the buttons in the GUI.
     * Creates and initializes various objects based on the action performed.
     *
     * @param e the ActionEvent object representing the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddC) {
            ClientAdd addClients = new ClientAdd();
        }
        if (e.getSource() == buttonEditC) {
            ClientEdit editClients = new ClientEdit();
        }
        if (e.getSource() == buttonDeleteC) {
            ClientRemove removeClient = new ClientRemove();
        }
        if (e.getSource() == buttonViewC) {
            ClientsTable showClients = new ClientsTable(true);
        }
        if (e.getSource() == buttonAddP) {
            ProductAdd addProduct = new ProductAdd();
        }
        if (e.getSource() == buttonEditP) {
            ProductEdit editProduct = new ProductEdit();
        }
        if (e.getSource() == buttonDelP) {
            ProductRemove removeProduct = new ProductRemove();
        }
        if (e.getSource() == buttonViewP) {
            ProductTable showProducts = new ProductTable(true);
        }
        if (e.getSource() == buttonNewOrder) {
            OrderAdd newOrder = new OrderAdd();
        }
        if (e.getSource() == buttonViewOrders) {
            OrdersTable showOrders = new OrdersTable(true);
        }
        if (e.getSource() == buttonDeleteOrder) {
            OrderRemove showOrders = new OrderRemove();
        }

        if (e.getSource() == buttonEditOrder) {
            OrderEdit showOrders = new OrderEdit();
        }

    }
}
