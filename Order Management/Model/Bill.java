package org.tpIliesOana.model;
/**
 * The Bill class represents a bill with purchase or transaction information.
 * It is an immutable record class with attributes: name, prName, nrTelefon, price, and address.
 *
 * <p> Provides a convenient way to store and retrieve bill information.
 * The attributes are accessible through getters.
 *
 * <p> The Bill record class is immutable for data integrity and thread-safety.
 */

public record Bill(String name, String prName, String nrTelefon, int price, String address) {

        /**
         * Constructs a new instance of the Bill record with the specified attribute values.
         *
         * @param name      the name associated with the bill
         * @param prName    the product name associated with the bill
         * @param nrTelefon the phone number associated with the bill
         * @param price     the price associated with the bill
         * @param address   the address associated with the bill
         */
        public Bill {

        }

        /**
         * Retrieves the name associated with the bill.
         *
         * @return the name associated with the bill
         */
        public String name() {
                return name;
        }

        /**
         * Retrieves the product name associated with the bill.
         *
         * @return the product name associated with the bill
         */
        public String prName() {
                return prName;
        }

        /**
         * Retrieves the phone number associated with the bill.
         *
         * @return the phone number associated with the bill
         */
        public String nrTelefon() {
                return nrTelefon;
        }

        /**
         * Retrieves the price associated with the bill.
         *
         * @return the price associated with the bill
         */
        public int price() {
                return price;
        }

        /**
         * Retrieves the address associated with the bill.
         *
         * @return the address associated with the bill
         */
        public String address() {
                return address;
        }
}
