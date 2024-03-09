package org.tpIliesOana.dao;

import org.tpIliesOana.model.Clients;

/**
 * The ClientDAO class is responsible for managing data access operations for Clients objects.
 * It extends the AbstractDAO class with a type parameter of Clients.
 *
 * <p> The ClientDAO class provides methods for CRUD (Create, Read, Update, Delete) operations
 * specific to Clients objects. It encapsulates the logic for interacting with a data source,
 * such as a database, and handles the necessary database connections and statements.
 */
public class ClientDAO extends AbstractDAO<Clients> {

    /**
     * Constructs a new instance of the ClientDAO class.
     * This constructor is responsible for initializing the ClientDAO object.
     */
    public ClientDAO() {

    }
}
