package me.theeninja.cargo;

import me.theeninja.cargo.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByTokenString(String tokenString);
    Optional<VerificationToken> findByVerificationRequester(User account);
}
