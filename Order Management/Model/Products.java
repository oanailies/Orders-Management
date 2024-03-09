
/**

 The Products class represents a product with information such as ID, name, price, stock, and details.

 <p> The class provides constructors and getter/setter methods to manipulate the product data.
 The ID field is generated automatically when creating a new instance of the Products class.

 The toString() method returns a string representation of the product object.
 */

package org.tpIliesOana.model;
public class Products {

    private int ID;
    private String productName;
    private String detalii;
    private int productPrice;
    private int productStock;

    /**

     Default constructor for the Products class.
     */

    public Products() {
    }

    /**

     Constructs a product with the specified name, price, stock, and details.
     @param name The name of the product.
     @param price The price of the product.
     @param stock The stock quantity of the product.
     @param detalii The details of the product.
     */
    public Products(String name, int price, int stock, String detalii) {
        this.detalii=detalii;
        this.productName = name;
        this.productPrice = price;
        this.productStock = stock;
    }

    /**

     Constructs a product with the specified ID, name, price, and stock.
     @param productID The ID of the product.
     @param productName The name of the product.
     @param productPrice The price of the product.
     @param productStock The stock quantity of the product.
     */
    public Products(int productID, String productName, int productPrice, int productStock) {
        this.ID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }
    /**

     Returns the ID of the product.
     @return The ID of the product.
     */
    public int getID() {
        return ID;
    }
    /**

     Sets the ID of the product.
     @param ID The ID to be set for the product.
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**

     Returns the name of the product.
     @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }
    /**

     Sets the name of the product.
     @param productName The name to be set for the product.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**

     Returns the price of the product.
     @return The price of the product.
     */
    public int getProductPrice() {
        return productPrice;
    }
    /**

     Sets the price of the product.
     @param productPrice The price to be set for the product.
     */
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    /**

     Returns the stock quantity of the product.
     @return The stock quantity of the product.
     */
    public int getProductStock() {
        return productStock;
    }
    /**

     Sets the stock quantity of the product.
     @param productStock The stock quantity to be set for the product.
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    /**

     Returns the details of the product.
     @return The details of the product.
     */

    public String getDetalii() {
        return detalii;
    }

    /**

     Sets the details of the product.
     @param detalii The details to be set for the product.
     */
    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }
    /**

     Returns a string representation of the product object.
     @return A string representation of the product object.
     */
    @Override
    public String toString() {
        return "Product: " +
                "[ id=" + ID +
                ", name=" + productName +
                ", price=" + productPrice +
                ", stock=" + productStock +
                ", detalii=" + detalii +
                "]";
    }
}