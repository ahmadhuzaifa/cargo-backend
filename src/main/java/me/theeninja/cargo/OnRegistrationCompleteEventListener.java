package me.theeninja.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import me.theeninja.cargo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class OnRegistrationCompleteEventListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    private static final String VERIFICATION_TOKEN_SUBJECT = "CarGO Registration Confirmation";

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        val account = onRegistrationCompleteEvent.getUser();
        val verificationTokenString = UUID.randomUUID().toString();

        this.getUserService().newVerificationToken(account, verificationTokenString);

        val configmrationURL = onRegistrationCompleteEvent.getAppURL() + "verify?token=" + verificationTokenString;

        val recipientEmailAddress = ""; // TODO

        val recipientEmailMessage = new SimpleMailMessage();

        recipientEmailMessage.setTo(recipientEmailAddress);
        recipientEmailMessage.setSubject(VERIFICATION_TOKEN_SUBJECT);
        recipientEmailMessage.setText(configmrationURL);

        getJavaMailSender().send(recipientEmailMessage);
    }
}
