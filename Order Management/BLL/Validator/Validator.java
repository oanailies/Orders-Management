/**

 The {@code Validator} interface provides a contract for validating objects of type {@code T}.
 @param <T> the type of object to be validated
 */
package org.tpIliesOana.bll.Validators;
public interface Validator<T> {
    /**
     * Validates an object of type {@code T}.
     *
     * @param t the object to be validated
     */
    public void validate(T t);
}
