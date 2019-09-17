package me.theeninja.cargo.user.enduser;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import me.theeninja.cargo.user.EndUserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class EndUserForm {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String emailAddress;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmedPassword;

    @NotNull
    private byte[] driversLicenseImage;

    @NotNull
    @NotEmpty
    private Set<EndUserRole> endUserRoles;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String socialSecurityNumber;
}
