package me.theeninja.cargo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import me.theeninja.cargo.*;
import me.theeninja.cargo.user.enduser.EndUser;
import me.theeninja.cargo.user.enduser.EndUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService<U extends User> implements UserDetailsService {
    private final UserRepository<U> endUserRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserCredentialsDisambiguator userCredentialsDisambiguator;

    /* public EndUser registerUserAccount(final EndUserForm endUserForm) throws UserCredentialExistsException {
        val emailAddress = endUserForm.getEmailAddress();

        if (getEndUserRepository().findByEmailAddress(emailAddress).isPresent()) {
            throw new EmailExistsException(String.format("The email %s is already registered.", emailAddress));
        }

        val socialSecurityNumber = endUserForm.getSocialSecurityNumber();
        val hashedSocialSecurityNumber = socialSecurityNumber;

        if (getEndUserRepository().findBySocialSecurityNumber(hashedSocialSecurityNumber).isPresent()) {
            throw new SocialSecurityNumberExistsException(String.format("The social security number %s is already registered.", socialSecurityNumber));
        }

        val phoneNumber = endUserForm.getPhoneNumber();

        if (getEndUserRepository().findByPhoneNumber(phoneNumber).isPresent()) {
            throw new PhoneNumberExistsException(String.format("The phone number %s is already registered.", phoneNumber));
        }

        val user = new EndUser();

        user.getEndUserRoles().addAll(endUserForm.getEndUserRoles());
        user.setFirstName(endUserForm.getFirstName());
        user.setLastName(endUserForm.getLastName());
        user.setEmailAddress(endUserForm.getEmailAddress());
        user.setDriversLicenseImage(endUserForm.getDriversLicenseImage());
        user.setPassword(user.getPassword());
        user.setInsuranceCardImage(user.getInsuranceCardImage());
        user.setSocialSecurityNumber(user.getSocialSecurityNumber());

        return null;
    } */

    @Override
    public UserDetails loadUserByUsername(final String emailAddress) throws UsernameNotFoundException {
        return getEndUserRepository().findByEmailAddress(emailAddress)
                                     .orElseThrow(() -> new UsernameNotFoundException(String.format("The email address %s is not registered.", emailAddress)));
    }

    public VerificationToken newVerificationToken(final User user, final String verificationTokenString) {
        val verificationToken = new VerificationToken();
        verificationToken.setVerificationRequester(user);
        verificationToken.setTokenString(verificationTokenString);

        return verificationToken;
    }

    public VerificationToken getVerificationToken(final String verificationTokenString) {
        val verificationToken = getVerificationTokenRepository().findByTokenString(verificationTokenString);

        return verificationToken.orElseThrow(VerificationTokenNotFoundException::new);
    }

    public void verifyUser(final U user) {
        user.setEnabled(true);
    }
}
