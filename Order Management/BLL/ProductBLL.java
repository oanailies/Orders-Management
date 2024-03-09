
/**

 The {@code ProductBLL} class represents the business logic layer for managing products.
 It provides methods for finding, inserting, updating, and deleting products.
 @since 1.0
 */
package org.tpIliesOana.bll;

import org.tpIliesOana.dao.ProductDAO;
import org.tpIliesOana.model.Products;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    /**
     * Constructs a new {@code ProductBLL} instance.
     * Initializes the product DAO.
     */
    private ProductDAO productDAO;

    /**
     * Constructs a new {@code ProductBLL} instance.
     * Initializes the product DAO.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Finds a product by ID.
     *
     * @param id the ID of the product to find
     * @return the product with the specified ID
     * @throws NoSuchElementException if the product is not found
     */
    public Products findProductById(int id) {
        Products product = productDAO.findById(id);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Produsul nu a fost gasit");
            throw new NoSuchElementException("Produsul cu id " + id + " nu exista");
        }
        return product;
    }

    /**
     * Retrieves the field names of the {@link Products} entity.
     *
     * @return an array of field names
     */
    public String[] getFieldNames() {
        return productDAO.getFieldNames();
    }

    /**
     * Retrieves a two-dimensional array representation of the list of products.
     *
     * @return a two-dimensional array representing the list of products
     */
    public String[][] getListOfProducts() {
        String[][] listOfProducts = productDAO.convertListOfObjectsToArray(productDAO.findAll());
        return listOfProducts;
    }

    /**
     * Inserts a new product.
     *
     * @param product the product to be inserted
     * @return the inserted product
     */
    public Products insertProduct(Products product) {
        return productDAO.insert(product);
    }

    /**
     * Deletes a product by ID.
     *
     * @param productID the ID of the product to be deleted
     */
    public void deleteProduct(int productID) {
        productDAO.delete(productID);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public List<Products> findAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Updates an existing product.
     *
     * @param product the product to be updated
     * @return the updated product
     */
    public Products updateProduct(Products product) {
        return productDAO.update(product, product.getID());
    }

}