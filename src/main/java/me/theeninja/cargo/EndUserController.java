package me.theeninja.cargo;

import me.theeninja.cargo.user.UserController;
import me.theeninja.cargo.user.UserCredentialExistsException;
import me.theeninja.cargo.user.enduser.EndUser;
import me.theeninja.cargo.user.enduser.EndUserForm;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndUserController extends UserController<EndUser> {
    @PostMapping("/register")
    public void attemptAccountRegistration(final @ModelAttribute("endUserForm") EndUserForm endUserForm) {
        try {
            getUserService().registerUserAccount(endUserForm);
        } catch (UserCredentialExistsException e) {
            e.printStackTrace();
        }
    }
}
