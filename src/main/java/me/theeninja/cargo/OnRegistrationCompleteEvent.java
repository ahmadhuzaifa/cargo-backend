package me.theeninja.cargo;

import lombok.Getter;
import me.theeninja.cargo.user.User;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appURL;
    private final String locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final String appURL, final String locale) {
        super(user);
        this.appURL = appURL;
        this.locale = locale;
        this.user = user;
    }
}
