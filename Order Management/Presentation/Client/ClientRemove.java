package org.tpIliesOana.presentation.Client;

import org.tpIliesOana.bll.ClientBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ClientEdit class is responsible for handling the GUI and actions related to client editing.
 */

public class ClientRemove implements ActionListener {
    private JFrame frame = new JFrame("Remove client");
    private JPanel panelClient = new JPanel();
    private JLabel labelTitle = new JLabel("Introdu id-ul clientului pe care doresti sa-l stergi");
    private JTextField ID = new JTextField();
    private JButton buttonDelete = new JButton("Sterge");


    /**
     * Constructs a new ClientRemove instance and initializes the GUI.
     */
    public ClientRemove() {

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDelete) {
            deleteClient();
        }
    }

    /**
     * Deletes the client from the database based on the provided ID.
     */
    private void deleteClient() {
        if (isIDValid()) {
            int clientID = getClientID();
            deleteClientFromDatabase(clientID);
            showMessage("Clientul a fost sters");
            closeFrame();
        } else {
            showMessage("ID invalid");
        }
    }

    /**
     * Checks if the provided ID is valid.
     *
     * @return true if the ID is valid, false otherwise
     */
    private boolean isIDValid() {
        return !ID.getText().equals("");
    }

    /**
     * Retrieves the client ID from the input field.
     *
     * @return the client ID
     */
    private int getClientID() {
        return Integer.parseInt(ID.getText());
    }

    /**
     * Deletes the client from the database.
     *
     * @param clientID the ID of the client to be deleted
     */
    private void deleteClientFromDatabase(int clientID) {
        ClientBLL clientBLL = new ClientBLL();
        clientBLL.deleteClient(clientID);
    }

    /**
     * Displays a message dialog with the given message.
     *
     * @param message the message to display
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(buttonDelete, message);
    }

    /**
     * Closes the current frame.
     */
    private void closeFrame() {
        frame.dispose();
    }
}
