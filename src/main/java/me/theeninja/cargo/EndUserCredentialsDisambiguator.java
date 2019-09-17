package me.theeninja.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import me.theeninja.cargo.user.EndUserRepository;
import me.theeninja.cargo.user.PhoneNumberExistsException;
import me.theeninja.cargo.user.SocialSecurityNumberExistsException;
import me.theeninja.cargo.user.UserCredentialsDisambiguator;
import me.theeninja.cargo.user.UserRepository;
import me.theeninja.cargo.user.enduser.EndUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class EndUserCredentialsDisambiguator extends UserCredentialsDisambiguator<EndUser> {
    private final EndUserRepository endUserRepository = null; // TODO

    public EndUserCredentialsDisambiguator(UserRepository<EndUser> userRepository) {
        super(userRepository);
    }

    @Override
    public Collection<UserCredentialDisambiguator<?, ?>> getUserCredentialDisambiguators() {
        val baseUserCredentialDisambiguators = Stream.of(super.getUserCredentialDisambiguators()).flatMap(Collection::stream);

        val endUserSpecificCredentialDisambiguators = Stream.of(
            new UserCredentialDisambiguator<String, PhoneNumberExistsException>(EndUser::getPhoneNumber, getEndUserRepository()::findByPhoneNumber, PhoneNumberExistsException::new),
            new UserCredentialDisambiguator<String, SocialSecurityNumberExistsException>(EndUser::getSocialSecurityNumber, getEndUserRepository()::findBySocialSecurityNumber, SocialSecurityNumberExistsException::new)
        );

        val endUserCredentialDisambiguators = Stream.concat(baseUserCredentialDisambiguators, endUserSpecificCredentialDisambiguators);

        return endUserSpecificCredentialDisambiguators.collect(Collectors.toUnmodifiableSet());
    }
}
