package me.theeninja.cargo.user;

public class SocialSecurityNumberExistsException extends UserCredentialExistsException {
    public SocialSecurityNumberExistsException(final Object credentialValue) {
        super("Social Security Number", credentialValue);
    }
}
