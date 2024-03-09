/**

 The Orders class represents an order with information such as ID, client ID, product ID, order date, and quantity.

 <p> The class provides constructors and getter/setter methods to manipulate the order data.
 The ID field is generated automatically when creating a new instance of the Orders class.

 The toString() method returns a string representation of the order object.
 */
package org.tpIliesOana.model;

public class Orders {
    private int ID;
    private int cID;
    private int pID;
    private String Odate;
    private int quantity;

    /**

     Constructs an Orders object with the specified client ID, product ID, order date, and quantity.
     @param clientID The ID of the client associated with the order.
     @param productID The ID of the product associated with the order.
     @param Odate The date of the order.
     @param quantity The quantity of the ordered product.
     */

    public Orders(int clientID, int productID, String Odate, int quantity) {
        this.cID = clientID;
        this.pID = productID;
        this.Odate=Odate;
        this.quantity = quantity;
    }

    public Orders() {
    }

    public Orders(int orderID, int clientID, int productID, int quantity) {
        this.ID = orderID;
        this.cID = clientID;
        this.pID = productID;
        this.quantity = quantity;
    }

    /**

     Returns the ID of the order.
     @return The ID of the order.
     */
    public int getOrdersID() {
        return ID;
    }
    /**

     Returns the ID of the order.
     @return The ID of the order.
     */
    public int getID() {
        return ID;
    }
    /**

     Sets the ID of the order.
     @param ID The ID to be set for the order.
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**

     Returns the client ID associated with the order.
     @return The client ID associated with the order.
     */
    public int getcID() {
        return cID;
    }
    /**

     Sets the client ID associated with the order.
     @param cID The client ID to be set for the order.
     */
    public void setcID(int cID) {
        this.cID = cID;
    }
    /**

     Returns the product ID associated with the order.
     @return The product ID associated with the order.
     */
    public int getpID() {
        return pID;
    }
    /**

     Sets the product ID associated with the order.
     @param pID The product ID to be set for the order.
     */
    public void setpID(int pID) {
        this.pID = pID;
    }
    /**

     Returns the quantity of the ordered product.
     @return The quantity of the ordered product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**

     Sets the quantity of the ordered product.
     @param quantity The quantity to be set for the ordered product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**

     Returns the date of the order.
     @return The date of the order.
     */
    public String getOdate() {
        return Odate;
    }
    /**

     Sets the date of the order.
     @param odate The date to be set for the order.
     */
    public void setOdate(String odate) {
        Odate = odate;
    }
    /**

     Returns a string representation of the order object.
     @return A string representation of the order object.
     */
    @Override
    public String toString() {
        return "Order: " +
                "[ id=" + ID +
                ", client id=" + cID +
                ", product id=" + pID +
                ", quantity=" + quantity +
                ", date=" + Odate +
                "]";
    }
}