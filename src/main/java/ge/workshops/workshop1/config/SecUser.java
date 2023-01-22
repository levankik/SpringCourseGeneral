package ge.workshops.workshop1.config;

import ge.workshops.workshop1.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecUser implements UserDetails {
    private String username;
    private String password;
    private Boolean enabled;
    private Integer id;

    public SecUser(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.enabled = user.getActive();
        this.id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }
}
