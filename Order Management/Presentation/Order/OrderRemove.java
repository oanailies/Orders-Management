package org.tpIliesOana.presentation.Order;

import org.tpIliesOana.bll.ClientBLL;
import org.tpIliesOana.bll.OrdersBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderRemove implements ActionListener {
    private JFrame frame = new JFrame("Remove order");
    private JPanel panelClient = new JPanel();
    private JLabel labelTitle = new JLabel("Introdu id-ul comenzii pe care doresti sa-l stergi");
    private JTextField ID = new JTextField();
    private JButton buttonDelete = new JButton("Sterge");


    /**
     * Constructs a new instance of the OrderRemove class.
     */
    public OrderRemove() {

        labelTitle.setFont(new Font("Times New Roman", Font.BOLD, 14));
        buttonDelete.setFocusPainted(false);
        buttonDelete.setContentAreaFilled(false);

        panelClient.setLayout(new GridLayout(12, 2));
        panelClient.add(labelTitle);
        panelClient.add(ID);
        panelClient.add(buttonDelete);

        panelClient.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonDelete.addActionListener(this);
        buttonDelete.setPreferredSize(new Dimension(100, 20));
        panelClient.setBackground(Color.GREEN);
        frame.setLayout(new BorderLayout());
        frame.add(panelClient, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelClient.setLayout(null);
        frame.setSize(350, 150);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    /**
     * Handles the action performed event for the button.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDelete) {
            if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog(buttonDelete, "ID invalid");
            } else {
                OrdersBLL ordersBLL = new OrdersBLL();
                ordersBLL.deleteClient(Integer.parseInt(ID.getText()));
                JOptionPane.showMessageDialog(buttonDelete, "Comanda a fost stearsa a fost sters!");
                frame.dispose();
            }
        }
    }
}
