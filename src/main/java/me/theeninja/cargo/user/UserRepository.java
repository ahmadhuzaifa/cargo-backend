package me.theeninja.cargo.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository<U extends User> extends CrudRepository<U, Long> {
    Optional<U> findByUsername(String username);
}
