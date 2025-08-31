package com.coding.app.services;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${server.admin.username}")
    private String defaultAdminUsername;

    @Value("${server.admin.password}")
    private String defaultManagerAdminPassword;

    @Value("${server.admin.email}")
    private String defaultAdminEmail;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HistoryService historyService;

    public void createAdmin() throws InvalidObjectException {
        final User user = new User();
        user.setValidated(true);
        user.setUsername(defaultAdminUsername);
        user.setEmail(defaultAdminEmail);
        user.setPassword(defaultManagerAdminPassword);
        final HashMap<String, String> errors = Utils.validate(user);
        if (errors.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            user.setValidated(true);
            user.addRole(ServerRole.ADMIN);
            userRepository.save(user);
            historyService.addHistory("New administrator added " + user.getUsername());
        }else {
            throw new InvalidObjectException("Invalid user object", errors);
        }
    }

    public User createManager(final User user) throws InvalidObjectException {
        user.setPassword(defaultManagerAdminPassword);
        final HashMap<String, String> errors = Utils.validate(user);
        if (errors.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            user.setValidated(true);
            user.addRole(ServerRole.MANAGER);
            userRepository.save(user);
            historyService.addHistory("New manager added " + user.getUsername());
            return user;
        }else {
            throw new InvalidObjectException("Invalid user object", errors);
        }
    }

    public boolean adminExists() {
        return userRepository.findAll().stream().anyMatch(user -> user.getRoles().contains(ServerRole.ADMIN.name()));
    }

    public void banUser(final String username) throws NotFoundException {
        User user = userRepository.findById(username).orElseThrow(()-> new NotFoundException("User not found"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    public List<User> findUsersByFilter(final Predicate<User> filter) {
        return userRepository.findAll().stream()
                .filter(filter)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public User findByUsername(final String username) throws NotFoundException {
        return userRepository.findById(username).orElseThrow(()-> new NotFoundException("User not found"));
    }
}
