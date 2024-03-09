
/**

 The ClientAdd class represents a GUI form for adding a new client.
 It implements the ActionListener interface to handle button events.
 The form contains input fields for the client's ID, name, address, email, and phone number.
 The class provides methods for creating the form, setting the panel font, validating input data,
 creating a client object from the input fields, showing messages, and closing the form.
 */
package org.tpIliesOana.presentation.Client;

import org.tpIliesOana.bll.ClientBLL;
import org.tpIliesOana.model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAdd implements ActionListener {
    private JPanel panelClient = new JPanel();
    private JFrame frame = new JFrame("Client Add");
    private JTextField ID =new JTextField();
    private JTextField Name = new JTextField();
    private JTextField Address = new JTextField();
    private JTextField Email = new JTextField();

    private JTextField NrTelefon =new JTextField();
    private JButton buttonAdaugare = new JButton("Adauga");

    /**
     * Sets the font for all components within a container and its sub-containers.
     *
     * @param container The container to set the font for.
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
     * Constructs a new instance of the ClientAdd class.
     * Creates the GUI form for adding a new client.
     * Sets the layout, adds labels, input fields, and buttons to the panel.
     * Sets the panel and frame properties, and makes them visible.
     */
    public ClientAdd() {

        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 12);
        setPanelFont(panelClient, timesNewRomanFont);
        JLabel labelTitle = new JLabel("Datele clientului ");
        JLabel labelId=new JLabel("ID ");
        JLabel labelName = new JLabel("Nume ");
        JLabel labelAddress = new JLabel("Adresa ");
        JLabel labelEmail = new JLabel("E-mail ");
        JLabel labelNrTelefon=new JLabel("Nr telefon ");

        buttonAdaugare.setBackground(Color.BLUE);
        buttonAdaugare.setPreferredSize(new Dimension(100, 20));
        panelClient.setLayout(new GridLayout(12, 2));
        panelClient.add(labelTitle);
        panelClient.add(new JLabel(""));
        panelClient.add(labelName);
        panelClient.add(Name);
        panelClient.add(labelAddress);
        panelClient.add(Address);
        panelClient.add(labelEmail);
        panelClient.add(Email);
        panelClient.add(labelNrTelefon);
        panelClient.add(NrTelefon);
        panelClient.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonAdaugare.addActionListener(this);
        buttonAdaugare.setContentAreaFilled(false);
        buttonAdaugare.setFocusPainted(false);
        panelClient.setBackground(Color.GREEN);

        frame.setLayout(new BorderLayout());
        frame.add(panelClient, BorderLayout.CENTER);
        frame.add(buttonAdaugare, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        panelClient.setLayout(null);
        frame.setSize(400, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * Handles the button click events.
     * Performs validation on the input fields and displays appropriate messages.
     * Creates a client object from the input fields and inserts it into the database.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdaugare) {
            if (Address.getText().equals("") || Name.getText().equals("") || Email.getText().equals("") || NrTelefon.getText().equals("")) {
                showMessage("Nu s-au completat toate campurile");
            } else if (!isValidEmail(Email.getText())) {
                showMessage("Email Invalid");
            } else if (!isValidPhoneNumber(NrTelefon.getText())) {
                showMessage("Numar de telefon invalid");
            } else {
                showMessage("Clientul a fost adaugat");
                Clients client = createClientFromInputs();
                ClientBLL clientBLL = new ClientBLL();
                clientBLL.insertClient(client);
                closeFrame();
            }
        }
    }

    /**
     * Displays a message dialog with the specified message.
     *
     * @param message The message to be displayed.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(buttonAdaugare, message);
    }

    /**
     * Validates an email address.
     *
     * @param email The email address to be validated.
     * @return True if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    /**
     * Validates a phone number.
     *
     * @param phoneNumber The phone number to be validated.
     * @return True if the phone number is valid, false otherwise.
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10;
    }


    /**
     * Creates a client object from the input fields.
     *
     * @return The created client object.
     */
    private Clients createClientFromInputs() {
        String name = Name.getText();
        String address = Address.getText();
        String email = Email.getText();
        String phoneNumber = NrTelefon.getText();
        return new Clients(name, address, email, phoneNumber);
    }
    /**
     * Closes the form.
     */

    private void closeFrame() {
        frame.dispose();
    }

}
