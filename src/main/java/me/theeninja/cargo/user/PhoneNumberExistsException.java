package me.theeninja.cargo.user;

public class PhoneNumberExistsException extends UserCredentialExistsException {
    public PhoneNumberExistsException(final Object credentialValue) {
        super("Phone Number", credentialValue);
    }
}
