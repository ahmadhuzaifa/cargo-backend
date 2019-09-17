package me.theeninja.cargo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Getter
@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class UserCredentialsDisambiguator<U extends User> {
    private final UserRepository<U> userRepository;

    @FunctionalInterface
    public interface CarGOUserCredentialGetter<U extends User, C> {
        C getCredential(U carGOUser);
    }

    @FunctionalInterface
    public interface UserCredentialFilterer<U extends User, C> {
        Optional<U> findUser(C credential);
    }

    @Data
    public
    class UserCredentialDisambiguator<C, E extends UserCredentialExistsException> {
        private final CarGOUserCredentialGetter<U, C> carGOUserCredentialGetter;
        private final UserCredentialFilterer<U, C> userCredentialFilterer;
        private final Function<Object, E> userCredentialExistsExceptionConstructor;


        public void forceDisambiguity(final U user) throws E {
            val credential = getCarGOUserCredentialGetter().getCredential(user);
            val userWithCredential = this.getUserCredentialFilterer().findUser(credential);

            if (userWithCredential.isPresent()) {
                throw getUserCredentialExistsExceptionConstructor().apply(credential);
            }
        }
    }

    public Collection<UserCredentialDisambiguator<?, ?>> getUserCredentialDisambiguators() {
        return Set.of(
            new UserCredentialDisambiguator<>(User::getUsername, getUserRepository()::findByUsername, UsernameExistsException::new)
        );
    }
}
