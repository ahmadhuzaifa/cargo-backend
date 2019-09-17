package me.theeninja.cargo.user;

import me.theeninja.cargo.user.enduser.EndUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EndUserRepository extends UserRepository<EndUser> {
    Optional<EndUser> findBySocialSecurityNumber(String socialSecurityNumber);
    Optional<EndUser> findByPhoneNumber(String phoneNumber);
}