package me.theeninja.cargo;

public class VerificationTokenNotFoundException extends Exception {
    private static final String VERIFICATION_TOKEN_NOT_FOUND_MESSAGE = "The verification token %s was never issued.";

    public VerificationTokenNotFoundException(final String verificationToken) {
        super(String.format(VERIFICATION_TOKEN_NOT_FOUND_MESSAGE, verificationToken));
    }
}
