package org.tpIliesOana.presentation.Client;

import org.tpIliesOana.bll.ClientBLL;
import org.tpIliesOana.model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ClientEdit class is responsible for handling the GUI and actions related to client editing.
 */
public class ClientEdit implements ActionListener {
    private JPanel panelClient = new JPanel();
    private JFrame frame = new JFrame("Client");
    private JTextField Name = new JTextField();
    private JTextField Address = new JTextField();
    private JTextField Email = new JTextField();

    private JTextField NrTelefon =new JTextField();

    private JButton btnEdit = new JButton("Modifica");
    private JTextField Search = new JTextField();
    private JButton btnSearch = new JButton("Cauta");


    /**
     * Sets the font of all components within a container.
     *
     * @param container the container to set the font for
     * @param font      the font to set
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
     * Constructs a new ClientEdit instance and initializes the GUI.
     */
    public ClientEdit() {

        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panelClient, timesNewRomanFont);
        JLabel labelTitle = new JLabel("Datele clientului ");
        JLabel labelId=new JLabel("ID ");
        JLabel labelName = new JLabel("Nume ");
        JLabel labelAddress = new JLabel("Adresa ");
        JLabel labelEmail = new JLabel("E-mail ");
        JLabel labelNrTelefon=new JLabel("Nr telefon ");
        JLabel labelSearch = new JLabel("Introduceti ID-ul client-ului");
        btnEdit.setPreferredSize(new Dimension(100, 20));
        panelClient.setLayout(new GridLayout(12, 2));
        panelClient.setLayout(new GridLayout(12, 2));
        panelClient.add(labelTitle);
        panelClient.add(new JLabel(""));
        panelClient.add(labelSearch);
        panelClient.add(Search);
        panelClient.add(btnSearch);
        panelClient.add(new JLabel(""));
        panelClient.add(labelName);
        panelClient.add(Name);
        panelClient.add(labelAddress);
        panelClient.add(Address);
        panelClient.add(labelEmail);
        panelClient.add(Email);
        panelClient.add(labelNrTelefon);
        panelClient.add(NrTelefon);
        panelClient.add(btnEdit);
        panelClient.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnEdit.addActionListener(this);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setFocusPainted(false);

        btnSearch.addActionListener(this);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setFocusPainted(false);
        panelClient.setBackground(Color.GREEN);

        frame.setLayout(new BorderLayout());
        frame.add(panelClient, BorderLayout.CENTER);
        frame.add(btnEdit, BorderLayout.EAST);
        frame.add(btnEdit, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelClient.setLayout(null);
        frame.setSize(400, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            handleSearchAction();
        } else if (e.getSource() == btnEdit) {
            handleEditAction();
        }
    }

    /**
     * Handles the search action when the "Cauta" button is clicked.
     * Retrieves and displays client information based on the provided ID.
     */

    private void handleSearchAction() {
        if (Search.getText().isEmpty()) {
            showMessage("Introduceti un ID de client!");
        } else {
            ClientBLL clientBLL = new ClientBLL();
            Clients c = clientBLL.findClientById(Integer.parseInt(Search.getText()));
            if (c != null) {
                Name.setText(c.getCName());
                Address.setText(c.getCAddress());
                Email.setText(c.getCEmail());
                NrTelefon.setText(c.getCNrTelefon());
            } else {
                showMessage("Nu s-a gasit clientul cu ID-ul specificat");
            }
        }
    }

    /**
     * Handles the edit action when the "Modifica" button is clicked.
     * Updates the client information based on the provided input.
     */
    private void handleEditAction() {
        if (Search.getText().isEmpty() || Name.getText().isEmpty() || Address.getText().isEmpty() || Email.getText().isEmpty() || NrTelefon.getText().isEmpty()) {
            showMessage("Toate campurile trebuie completate!");
        } else if (!isValidEmail(Email.getText())) {
            showMessage("E-Mail invalid");
        }

        else {
            int clientId = Integer.parseInt(Search.getText());
            String name = Name.getText();
            String address = Address.getText();
            String email = Email.getText();
            String telefon = NrTelefon.getText();
            Clients updatedClient = new Clients(clientId, name, address, email, telefon);
            ClientBLL clientBLL = new ClientBLL();
            Clients success = clientBLL.updateClient(updatedClient);
            if (success != null) {
                showMessage("Datele clientului au fost modificate");
                frame.dispose();
            } else {
                showMessage("A apărut o eroare la modificarea clientului");
            }
        }
    }

    /**
     * Displays a message dialog with the given message.
     *
     * @param message the message to display
     */

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Checks if the given email address is valid.
     *
     * @param email the email address to check
     * @return true if the email address is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }


}
