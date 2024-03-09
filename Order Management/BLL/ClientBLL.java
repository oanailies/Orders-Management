
/**

 The {@code ClientBLL} class represents the business logic layer for managing clients.
 It provides methods for finding, inserting, updating, and deleting clients.
 It also manages a list of validators to validate client data before performing operations.
 @since 1.0
 */

package org.tpIliesOana.bll;
import org.tpIliesOana.bll.Validators.EmailValidator;
import org.tpIliesOana.bll.Validators.NrTelefonValidators;
import org.tpIliesOana.bll.Validators.Validator;
import org.tpIliesOana.model.Clients;
import org.tpIliesOana.dao.ClientDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Clients>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new {@code ClientBLL} instance.
     * Initializes the list of validators and the client DAO.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Clients>>();
        validators.add(new EmailValidator());
        validators.add(new NrTelefonValidators());
        clientDAO = new ClientDAO();
    }
    /**
     * Displays an error message in a dialog box.
     *
     * @param message the error message to be displayed
     */

    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Throws a {@link NoSuchElementException} with the specified client ID.
     *
     * @param id the ID of the client that was not found
     * @throws NoSuchElementException if the client is not found
     */
    private void throwClientNotFoundException(int id) {
        throw new NoSuchElementException("Clientul cu id-ul =" + id + " nu a fost gasit");
    }

    /**
     * Finds a client by ID.
     *
     * @param id the ID of the client to find
     * @return the client with the specified ID
     * @throws NoSuchElementException if the client is not found
     */
    public Clients findClientById(int id) {
        Clients client = clientDAO.findById(id);
        if (client == null) {
            displayErrorMessage("Nu exista acest client");
            throwClientNotFoundException(id);
        }
        return client;
    }

    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public List<Clients> findAllClients()
    {
        return clientDAO.findAll();
    }

    /**
     * Retrieves a two-dimensional array representation of the list of clients.
     *
     * @return a two-dimensional array representing the list of clients
     */
    public String[][] getListOfClients()
    {
        String[][] clientList = clientDAO.convertListOfObjectsToArray(clientDAO.findAll());
        return clientList;
    }


    /**

     Retrieves the field names of the {@link Clients} entity.
     @return an array of field names
     */
    public String[] getFieldNames() {
        return clientDAO.getFieldNames();
    }
    /**

     Inserts a new client.
     @param client the client to be inserted
     @return the inserted client
     */
    public Clients insertClient(Clients client) {
        for (Validator<Clients> validator : validators) {
            validator.validate(client);
        }
        return clientDAO.insert(client);
    }
    /**

     Updates an existing client.
     @param client the client to be updated
     @return the updated client
     */
    public Clients updateClient(Clients client) {
        return clientDAO.update(client, client.getID());
    }
    /**

     Deletes a client by ID.
     @param clientID the ID of the client to be deleted
     */
    public void deleteClient(int clientID) {
        clientDAO.delete(clientID);
    }



}