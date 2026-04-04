package org.example.sr_group_03_spring_mini_project.service.impl;

import org.example.sr_group_03_spring_mini_project.exception.auth.UserNotFoundException;
import org.example.sr_group_03_spring_mini_project.model.entity.AppUser;
import org.example.sr_group_03_spring_mini_project.repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository;

    public UserDetailsServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        AppUser user = authRepository.getAppUserByIdentifier(identifier);
        if (user == null) {
            throw new UserNotFoundException("User not found with identifier: " + identifier);
        }
        return user;
    }
}
