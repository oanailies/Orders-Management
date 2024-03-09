 package org.tpIliesOana.presentation.Order;

import org.tpIliesOana.model.Bill;
import org.tpIliesOana.bll.ClientBLL;
import org.tpIliesOana.bll.OrdersBLL;
import org.tpIliesOana.bll.ProductBLL;
import org.tpIliesOana.model.Clients;
import org.tpIliesOana.model.Orders;
import org.tpIliesOana.model.Products;
import org.tpIliesOana.presentation.Client.ClientsTable;
import org.tpIliesOana.presentation.Product.ProductTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

 /**
  * The OrderAdd class is responsible for handling the GUI and actions related to adding an order.
  */

public class OrderAdd implements ActionListener {
    private ClientsTable c = new ClientsTable(false);
    private ProductTable p = new ProductTable(false);
    JFrame frame = new JFrame("Create an order");
    private JTable clientTable;
    private JTable productTable;

    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;

    private JPanel panelOrder = new JPanel();
    private JTextField Quantity = new JTextField();

    private JTextField Date =new JTextField("");
    private JButton buttonAddOrder = new JButton("Add Order");
    private String[][] clientsData, productsData;
     /**
      * Constructs an OrderAdd object and initializes the GUI components.
      */
    public OrderAdd() {

        JLabel labelQuantity = new JLabel("Introdu numarul de produse dorit: ");
        JLabel labelDate=new JLabel("Data: ");
        JPanel panel1 = new JPanel(); //sus
        JPanel panel2 = new JPanel(); //jos
        JPanel panel3 = new JPanel(); //stanga
        JPanel panel4 = new JPanel(); //dreapta
        panel1.setBackground(Color.GREEN);
        panel2.setBackground(Color.GREEN);
        panel3.setBackground(Color.GREEN);
        panel4.setBackground(Color.GREEN);


        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();

        productsData = new String[300][5];
        clientsData = new String[300][5];

        List<Clients> listaClient = clientBLL.findAllClients();
        int index = 0;

        for (Clients c : listaClient) {
            clientsData[index][0] = String.valueOf(c.getID());
            clientsData[index][1] = c.getCName();
            clientsData[index][2] = c.getCAddress();
            clientsData[index][3] = c.getCEmail();
            clientsData[index][4]=c.getCNrTelefon();
            index++;
        }

        List<Products> listaProdus = productBLL.findAllProducts();
        index = 0;
        for (Products p : listaProdus) {
            productsData[index][0] = String.valueOf(p.getID());
            productsData[index][1] = p.getProductName();
            productsData[index][2] = String.valueOf(p.getProductPrice());
            productsData[index][3] = String.valueOf(p.getProductStock());
            productsData[index][4]=p.getDetalii();
            index++;
        }

        clientTable = c.getTable();
        productTable = p.getTable();

        JScrollPane scrollPanel1 = new JScrollPane(clientTable);
        JScrollPane scrollPanel2 = new JScrollPane(productTable);

        panel3.setLayout(new BorderLayout());
        panel3.add(scrollPanel1, BorderLayout.CENTER);

        panel4.setLayout(new BorderLayout());
        panel4.add(scrollPanel2, BorderLayout.CENTER);

        panel1.add(panel3);
        panel1.add(panel4);

             labelDate.setBounds(250, 1, 700, 35);
                  Date.setBounds(500, 1, 200, 35);
         labelQuantity.setBounds(250, 45, 700, 35);
              Quantity.setBounds(500, 45, 200, 35);
              buttonAddOrder.setBounds(300, 80, 200, 35);

        buttonAddOrder.addActionListener(this);
        panel2.add(labelDate);
        panel2.add(Date);
        panel2.add(labelQuantity);
        panel2.add(Quantity);
        panel2.add(buttonAddOrder);
        panel2.setLayout(null);
        panelOrder.add(panel1);
        panelOrder.add(panel2);
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setContentPane(panelOrder);
    }

     /**
      * Performs an action in response to an {@link ActionEvent}.
      *
      * @param e the ActionEvent representing the action
      */
     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource() == buttonAddOrder) {
             handleAddOrderAction();
         }
     }

     /**
      * Handles the action of adding an order.
      * Validates input and creates an order if all conditions are met.
      */
     private void handleAddOrderAction() {
         if (clientTable.getSelectedRow() == -1 || productTable.getSelectedRow() == -1) {
             JOptionPane.showMessageDialog(buttonAddOrder, "Selecteaza un client si ce produs doreste clientul");
         } else if (Quantity.getText().equals("") || Date.getText().equals("")) {
             JOptionPane.showMessageDialog(buttonAddOrder, "Toate campurile trebuie completate");
         } else {
             int selectedClientRow = clientTable.convertRowIndexToModel(clientTable.getSelectedRow());
             int selectedProductRow = productTable.convertRowIndexToModel(productTable.getSelectedRow());

             if (selectedClientRow == -1 || selectedProductRow == -1) {
                 JOptionPane.showMessageDialog(buttonAddOrder, "Selecteaza un client si ce produs doreste clientul");
                 return;
             }

             int clientID = Integer.parseInt(clientsData[selectedClientRow][0]);
             int productID = Integer.parseInt(productsData[selectedProductRow][0]);

             int availableStock = Integer.parseInt(productsData[selectedProductRow][3]);

             // System.out.println("ID Client selectat: " + clientID);
             // System.out.println("ID Produs selectat: " + productID);

             if (availableStock < Integer.parseInt(Quantity.getText())) {
                 JOptionPane.showMessageDialog(buttonAddOrder, "Stoc insuficient");
             } else {
                 createOrder(clientID, productID);
             }
         }
     }

     /**
      * Creates an order with the specified client ID and product ID.
      *
      * @param clientID  the ID of the client
      * @param productID the ID of the product
      */
     private void createOrder(int clientID, int productID) {
         OrdersBLL ordersBLL = new OrdersBLL();
         ProductBLL productBLL = new ProductBLL();
         ClientBLL clientBLL = new ClientBLL();
         Orders order = new Orders(clientID, productID, Date.getText(), Integer.parseInt(Quantity.getText()));
         Orders o = ordersBLL.insertOrders(order);
         Products p = productBLL.findProductById(productID);
         Clients c = clientBLL.findClientById(clientID);
         try {
             Bill bill = new Bill(c.getCName(), p.getProductName(), c.getCNrTelefon(), p.getProductPrice(), c.getCAddress());
             PrintWriter printWriter = new PrintWriter("C:\\Users\\Oana\\PT2023_30222_Ilies_Oana_Elena_3\\Orders_Management" + "\\bill" + o.getOrdersID() + ".txt");
             printWriter.write("Comanda dumnevoastra cu numarul " + o.getOrdersID() + " s-a efectuat cu succes!\n\n");
             printWriter.write("\nComanda plasa la data de: " + o.getOdate());
             printWriter.write("\nDate Client:\n Nume: " + c.getCName() + "\n E-Mail: " + c.getCEmail() + "\n Numar telefon: " + c.getCNrTelefon() + "\n\n");
             printWriter.write("Se va livra la adresa: " + c.getCAddress() + "\n\n");
             printWriter.write("Total de plata: " + p.getProductPrice() * Integer.parseInt(Quantity.getText()) + " ron");
             printWriter.close();
             JOptionPane.showMessageDialog(buttonAddOrder, "Comanda salvata");
             p.setProductStock(p.getProductStock() - o.getQuantity());
             productBLL.updateProduct(p);
             frame.dispose();
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }
     }
}