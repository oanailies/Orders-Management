package org.tpIliesOana.dao;

import org.tpIliesOana.model.Products;

/**
 * The ProductDAO class is responsible for managing data access operations for Products objects.
 * It extends the AbstractDAO class with a type parameter of Products.
 *
 * <p> The ProductDAO class provides methods for CRUD (Create, Read, Update, Delete) operations
 * specific to Products objects. It encapsulates the logic for interacting with a data source,
 * such as a database, and handles the necessary database connections and statements.
 */
public class ProductDAO extends AbstractDAO<Products> {

    /**
     * Constructs a new instance of the ProductDAO class.
     * This constructor is responsible for initializing the ProductDAO object.
     */
    public ProductDAO() {

    }
}
