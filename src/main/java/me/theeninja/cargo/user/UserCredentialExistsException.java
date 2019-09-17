package me.theeninja.cargo.user;

public class UserCredentialExistsException extends Exception {
    private static final String CREDENTIAL_EXISTS_MESSAGE = "The credential %s with value %s has already been registered.";

    public UserCredentialExistsException(final String credentialName, final Object credentialValue) {
        super(String.format(CREDENTIAL_EXISTS_MESSAGE, credentialName, credentialValue));
    }
}
