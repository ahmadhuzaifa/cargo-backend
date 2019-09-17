package me.theeninja.cargo.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@Data
@MappedSuperclass
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;

    private boolean isEnabled;

    @ElementCollection
    private Collection<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
