
/**

 The {@code OrdersBLL} class represents the business logic layer for managing orders.
 It provides methods for finding, inserting, and deleting orders.
 It also manages a list of validators to validate order data before performing operations.
 @since 1.0
 */
package org.tpIliesOana.bll;

import org.tpIliesOana.bll.Validators.DateValidator;
import org.tpIliesOana.bll.Validators.Validator;
import org.tpIliesOana.dao.OrdersDAO;
import org.tpIliesOana.model.Orders;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrdersBLL {

    private OrdersDAO ordersDAO = new OrdersDAO();
    private List<Validator<Orders>> validators;
    /**
     * Constructs a new {@code OrdersBLL} instance.
     * Initializes the list of validators and the orders DAO.
     */
    public OrdersBLL()
    {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new DateValidator());
        ordersDAO=new OrdersDAO();
    }

    /**
     * Retrieves the field names of the {@link Orders} entity.
     *
     * @return an array of field names
     */
    public String[] getFieldNames(){
        String[] fieldNames = ordersDAO.getFieldNames();
        return fieldNames;
    }

    /**
     * Retrieves a two-dimensional array representation of the list of orders.
     *
     * @return a two-dimensional array representing the list of orders
     */
    public String[][] getListOfOrders(){
        String[][] orderList = ordersDAO.convertListOfObjectsToArray(ordersDAO.findAll());
        return orderList;
    }

    /**
     * Inserts a new order.
     *
     * @param product the order to be inserted
     * @return the inserted order
     */

    public Orders insertOrders(Orders product)
    {
        return ordersDAO.insert(product);
    }


    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    public List<Orders> findAllOrders()
    {
        return ordersDAO.findAll();
    }

    /**
     * Deletes an order by ID.
     *
     * @param orderID the ID of the order to be deleted
     */
    public void deleteClient(int orderID)
    {
        ordersDAO.delete(orderID);
    }

    /**
     * Finds an order by ID.
     *
     * @param id the ID of the order to find
     * @return the order with the specified ID
     * @throws NoSuchElementException if the order is not found
     */
    public Orders findOrderById(int id) {
        Orders orders = ordersDAO.findById(id);
        if (orders == null) {
            JOptionPane.showMessageDialog(null, "Comanda nu a fost gasit");
            throw new NoSuchElementException("Comanda cu id " + id + "nu exista");
        }
        return orders;
    }
}