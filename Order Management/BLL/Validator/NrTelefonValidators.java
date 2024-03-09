/**

 The {@code NrTelefonValidators} class is responsible for validating phone numbers.
 It implements the {@link Validator} interface for the {@link Clients} class.
 */
package org.tpIliesOana.bll.Validators;
import org.tpIliesOana.model.Clients;

import java.util.regex.Pattern;

public class NrTelefonValidators implements Validator<Clients>{

    private static final String PHONE_PATTERN = "\\d{10}";

    /**
     * Validates the phone number of a client.
     *
     * @param t the client whose phone number is to be validated
     * @throws IllegalArgumentException if the phone number is invalid
     */
    public void validate(Clients t) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(t.getCNrTelefon()).matches()) {
            throw new IllegalArgumentException("Numar de telefon invalid");
        }
    }
}