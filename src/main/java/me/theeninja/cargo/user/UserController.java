package me.theeninja.cargo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import me.theeninja.cargo.VerificationToken;
import me.theeninja.cargo.VerificationTokenNotFoundException;
import me.theeninja.cargo.user.enduser.EndUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController<U extends User> {
    private final UserService<U> userService;

    @PostMapping("/verify")
    public void attemptAccountVerification(final @RequestParam("tokenString") String verificationTokenString) {
        try {
            val verificationToken = getUserService().getVerificationToken(verificationTokenString);

            val user = verificationToken.getVerificationRequester();

            val currentInstant = Instant.now();
            val expiryInstant = verificationToken.getVerificationRequestInstant().plus(1, ChronoUnit.DAYS);

            if (expiryInstant.isBefore(currentInstant)) {
                System.out.println("EXPIRED"); // TODO Finish
            }

            getUserService().verifyUser(user);
        } catch (VerificationTokenNotFoundException e) {
            e.printStackTrace();
        }
    }
}
