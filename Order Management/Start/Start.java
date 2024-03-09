package org.tpIliesOana.start;
import org.tpIliesOana.bll.ClientBLL;
import org.tpIliesOana.bll.OrdersBLL;
import org.tpIliesOana.bll.ProductBLL;
import org.tpIliesOana.model.Clients;
import org.tpIliesOana.model.Orders;
import org.tpIliesOana.model.Products;
import org.tpIliesOana.presentation.View;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * The entry point of the application.
 * Initializes and starts the application by creating an instance of the View class.
 */
public class Start {

    /**
     * The logger instance used for logging messages.
     */
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    /**
     * The main method of the application.
     * Creates an instance of the View class to start the application.
     *
     * @param args the command line arguments
     * @throws SQLException if a database access error occurs
     */
    public static void main(String[] args) throws SQLException {
        View start=new View();
       /* Clients client=new Clients("dummy name", "dummy address", "dummy@address.co", "0720000000");
        ClientBLL clientsBLL=new ClientBLL();
        int id= clientsBLL.insertClient(client).getID();
        if(id>0)
        {
            clientsBLL.findClientById(id);
        }
        RefractionClients.retrieveProperties(client);*/

      /*  Orders order=new Orders(1, 1, "2023-10-01", 1);
        OrdersBLL ordersBLL=new OrdersBLL();
        int id= ordersBLL.insertOrders(order).getID();
        if(id>0)
        {
            ordersBLL.findOrderById(id);
        }
        RefractionOrders.retrieveProperties(order);
       */

       /* Products products=new Products("dummy name", 1, 1, "dummy detail");
        ProductBLL productBLL=new ProductBLL();
        int id= productBLL.insertProduct(products).getID();
        if(id>0)
        {
            productBLL.findProductById(id);
        }
        RefractionProducts.retrieveProperties(products);*/



    }
}