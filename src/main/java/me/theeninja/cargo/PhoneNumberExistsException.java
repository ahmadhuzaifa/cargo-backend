package me.theeninja.cargo;

import me.theeninja.cargo.user.UserCredentialExistsException;

public class PhoneNumberExistsException extends UserCredentialExistsException {
    public PhoneNumberExistsException(final String credentialName, final Object credentialValue) {
        super(credentialName, credentialValue);
    }
}
