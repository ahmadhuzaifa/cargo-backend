package me.theeninja.cargo;

import lombok.Data;
import lombok.val;
import me.theeninja.cargo.user.User;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tokenString;

    @OneToOne
    @JoinColumn
    private User verificationRequester;

    private Instant verificationRequestInstant;

    public boolean isExpired() {
        val currentInstant = Instant.now();
        val instantOfExpiration = getVerificationRequestInstant().plus(1, ChronoUnit.DAYS);

        return currentInstant.isBefore(instantOfExpiration);
    }
}
