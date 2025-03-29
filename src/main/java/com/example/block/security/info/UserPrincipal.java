package com.example.block.security.info;

import com.example.block.global.constants.Constants;
import com.example.block.repository.UserRepository;
import com.example.block.security.enums.ERole;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    @Getter
    private final Integer id;
    @Getter private final ERole role;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal createByUserSecurityForm(UserRepository.UserSecurityForm form) {
        return UserPrincipal.builder()
                .id(form.getId())
                .password(form.getPassword())
                .authorities(null)
                .build();
    }

    public static UserPrincipal createByUserId(Integer userId, String password){
        return UserPrincipal.builder()
                .id(userId)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(Constants.USER_ROLE)))
                .password(password)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id.toString();
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

    public Integer getId() {
        return id;
    }
}
