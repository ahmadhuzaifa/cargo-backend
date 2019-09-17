package me.theeninja.cargo.user;

public class UsernameExistsException extends UserCredentialExistsException {
    public UsernameExistsException(final Object credentialValue) {
        super("Email Address", credentialValue);
    }
}
