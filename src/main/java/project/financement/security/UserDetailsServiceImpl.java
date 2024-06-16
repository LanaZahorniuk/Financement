package project.financement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Role;
import project.financement.entity.UserInfo;
import project.financement.repository.UserInfoRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User with login '" + username + "' not found");
        }

        return User.withUsername(username)
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .authorities(getAuthorities(Collections.singletonList(userInfo.getRole())))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            r.getAuthoritySet().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName())));
        }

        return authorities;
    }
}
