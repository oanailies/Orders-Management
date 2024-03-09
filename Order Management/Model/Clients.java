package org.tpIliesOana.model;


/**

 The Clients class represents a client with basic information.
 It provides constructors, getters, and setters to manipulate the client data.
 The ID field is automatically generated.
 The toString() method returns a string representation of the client object.
 */
public class Clients {
    private int ID;
    private String CName;
    private String CAddress;
    private String CEmail;
    private String CNrTelefon;

    /**
     * Default constructor for the Clients class.
     */
    public Clients() {
    }

    /**
     * Constructor for the Clients class with specified client details.
     *
     * @param clientName    the name of the client
     * @param clientAddress the address of the client
     * @param clientEmail   the email of the client
     * @param CNrTelefon    the phone number of the client
     */
    public Clients(String clientName, String clientAddress, String clientEmail, String CNrTelefon) {
        this.CName = clientName;
        this.CAddress = clientAddress;
        this.CEmail = clientEmail;
        this.CNrTelefon = CNrTelefon;
    }

    /**
     * Constructor for the Clients class with specified client details and ID.
     *
     * @param id             the ID of the client
     * @param clientName     the name of the client
     * @param clientAddress  the address of the client
     * @param clientEmail    the email of the client
     * @param CNrTelefon     the phone number of the client
     */
    public Clients(int id, String clientName, String clientAddress, String clientEmail, String CNrTelefon) {
        this.ID = id;
        this.CName = clientName;
        this.CAddress = clientAddress;
        this.CEmail = clientEmail;
        this.CNrTelefon = CNrTelefon;
    }

    /**
     * Get the ID of the client.
     *
     * @return the ID of the client
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the ID of the client.
     *
     * @param ID the ID of the client
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get the name of the client.
     *
     * @return the name of the client
     */
    public String getCName() {
        return CName;
    }

    /**
     * Set the name of the client.
     *
     * @param CName the name of the client
     */
    public void setCName(String CName) {
        this.CName = CName;
    }

    /**
     * Get the address of the client.
     *
     * @return the address of the client
     */
    public String getCAddress() {
        return CAddress;
    }

    /**
     * Set the address of the client.
     *
     * @param CAddress the address of the client
     */
    public void setCAddress(String CAddress) {
        this.CAddress = CAddress;
    }

    /**
     * Get the email of the client.
     *
     * @return the email of the client
     */
    public String getCEmail() {
        return CEmail;
    }

    /**
     * Set the email of the client.
     *
     * @param CEmail the email of the client
     */
    public void setCEmail(String CEmail) {
        this.CEmail = CEmail;
    }


    /**

     Returns the phone number of the client.
     @return The phone number of the client.
     */
    public String getCNrTelefon() {
        return CNrTelefon;
    }
    /**

     Sets the phone number of the client.
     @param CNrTelefon The phone number to be set for the client.
     */
    public void setCNrTelefon(String CNrTelefon) {
        this.CNrTelefon = CNrTelefon;
    }
    /**

     Returns a string representation of the client object.
     @return A string representation of the client object.
     */
    @Override
    public String toString() {
        return "Client: " +
                "[ id=" + ID +
                ", name=" + CName +
                ", address=" + CAddress +
                ", email=" + CEmail +
                ", phone number=" + CNrTelefon +
                "]";
    }
}