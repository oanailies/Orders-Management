/**
 * The {@code RefractionClients} class provides a utility method for retrieving and printing the properties of an object using reflection.
 * It allows accessing the fields of an object and printing their names and values.
 *
 * @since 1.0
 */
package org.tpIliesOana.start;

import java.lang.reflect.Field;
public class RefractionClients {
    /**
     * Retrieves and prints the properties of the given object.
     *
     * @param object the object whose properties need to be retrieved and printed
     */

    public static void retrieveProperties(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                System.out.println(field.getName() + "=" + value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
