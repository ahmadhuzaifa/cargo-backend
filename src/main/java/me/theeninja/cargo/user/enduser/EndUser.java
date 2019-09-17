package me.theeninja.cargo.user.enduser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.theeninja.cargo.user.EndUserRole;
import me.theeninja.cargo.user.User;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class EndUser extends User {
    private String phoneNumber;

    private byte[] driversLicenseImage;
    private byte[] insuranceCardImage;

    @ElementCollection
    private Set<EndUserRole> endUserRoles;

    private String socialSecurityNumber;

    private boolean enabled = false;
}
