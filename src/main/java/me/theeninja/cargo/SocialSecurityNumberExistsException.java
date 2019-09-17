package me.theeninja.cargo;

import me.theeninja.cargo.user.UserCredentialExistsException;

public class SocialSecurityNumberExistsException extends UserCredentialExistsException {
    public SocialSecurityNumberExistsException(final String credentialName, final Object credentialValue) {
        super(credentialName, credentialValue);
    }
}
