package org.tpIliesOana.bll.Validators;
import org.tpIliesOana.model.Clients;
import org.tpIliesOana.model.Orders;

import java.util.regex.Pattern;
/**
 * Validator for validating the date field of an order.
 */
public class DateValidator implements Validator<Orders>{

    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    /**
     * Validates the date field of an order.
     *
     * @param order the order to be validated
     * @throws IllegalArgumentException if the date is invalid
     */
    @Override
    public void validate(Orders order) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        if (!pattern.matcher(order.getOdate()).matches()) {
            throw new IllegalArgumentException("Invalid date");
        }
    }
}
