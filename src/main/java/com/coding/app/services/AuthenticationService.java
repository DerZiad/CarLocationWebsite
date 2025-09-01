package com.coding.app.services;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.exceptions.NotFoundException;
import com.coding.app.models.User;
import com.coding.app.models.VerificationCode;
import com.coding.app.models.enums.EmailType;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import com.coding.app.repository.VerificationCodeRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationCodeRepository verificationCodeRepository;

    private final EmailService emailService;

    @Value("${server.dns}")
    private String serverDns;

    public void registerUser(final User user, HttpServletRequest request) throws InvalidObjectException {

        final HashMap<String, String> errors = Utils.validate(user);
        if (usernameExists(user.getUsername())) {
            errors.put("username", "This username is already taken.");
        }
        if (errors.isEmpty()) {
            user.setEnabled(true);
            user.addRole(ServerRole.CLIENT);
            final String temporalPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            final VerificationCode verificationCode = createConfirmationCode(user, request);
            user.getVerificationCodes().add(verificationCode);
            userRepository.save(user);
            sendConfirmationEmail(user, verificationCode);
            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), temporalPassword));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new InvalidObjectException("The submitted data doesn't match the conventions specified.", errors);
        }
    }

    private VerificationCode createConfirmationCode(User user, HttpServletRequest request) {
        final VerificationCode verificationCode = new VerificationCode();
        verificationCode.setUser(user);
        verificationCode.setIp(request.getRemoteAddr());
        verificationCode.setType(EmailType.CONFIRMATION);
        verificationCode.setCode(generateCode());
        return verificationCode;
    }

    public void sendConfirmationEmail(final User user, final VerificationCode verificationCode) {
        final String confirmationUrl = serverDns + "/verification?code=" + verificationCode.getCode() + "&username=" + user.getUsername();
        final HashMap<String, String> bodyFields = new HashMap<>();
        bodyFields.put("name", user.getUsername());
        bodyFields.put("confirmationUrl", confirmationUrl);
        final String subject = "Complete your registration";
        final String to = user.getEmail();
        final EmailService.Email email = new EmailService.Email(to, subject, EmailType.CONFIRMATION, bodyFields);
        final Thread sendConfirmationEmail = new Thread(() -> {
            try {
                emailService.sendEmail(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        sendConfirmationEmail.start();
    }

    public void validateAccount(final String username, final String code) throws NotFoundException, InvalidObjectException {
        /*final User user = userRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found"));
        final VerificationCode verificationCode = verificationCodeRepository.findByUserAndCodeAndType(user, code, EmailType.CONFIRMATION)
                .orElseThrow(() -> new InvalidObjectException("Invalid confirmation code"));
        if (verificationCode.isDead()) {
            throw new InvalidObjectException("The confirmation code has expired");
        }
        user.setValidated(true);
        userRepository.save(user);
        verificationCode.setUser(null);
        verificationCodeRepository.delete(verificationCode);*/
    }

    // Privates Functions
    private String generateCode() {
        return String.valueOf(new Random().nextInt(9999));
    }

    private boolean usernameExists(final String username) {
        return userRepository.findById(username).isPresent();
    }
}
