package com.workintech.university.service;

import com.workintech.university.entity.Role;
import com.workintech.university.entity.User;
import com.workintech.university.exceptions.UserAlreadyRegisteredException;
import com.workintech.university.repository.RoleRepository;
import com.workintech.university.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent())
            throw new UserAlreadyRegisteredException("Email already registered");

        String encodePass = passwordEncoder.encode(password);
        Optional<Role> userRole = roleRepository.findRoleByAuthority("USER");

        if (userRole.isEmpty()) {
            Role role = new Role();
            role.setAuthority("USER");

            userRole = Optional.of(roleRepository.save(role));
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodePass);
        user.setRoles(Set.of(userRole.get()));

        return userRepository.save(user);
    }
}