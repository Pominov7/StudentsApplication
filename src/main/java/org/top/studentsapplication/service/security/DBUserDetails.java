package org.top.studentsapplication.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.top.studentsapplication.db.entity.User;

import java.util.Collection;
import java.util.Collections;

public class DBUserDetails implements UserDetails {

    // оборачиваем обычного пользователя
    private User dbUser;

    public DBUserDetails(User dbUser) {
        this.dbUser = dbUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(
                new SimpleGrantedAuthority("USER")
        );
    }

    @Override
    public String getPassword() {
        return dbUser.getPassword();
    }

    @Override
    public String getUsername() {
        return dbUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
