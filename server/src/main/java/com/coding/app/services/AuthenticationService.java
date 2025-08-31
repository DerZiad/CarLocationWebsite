package com.coding.app.services;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.models.User;
import com.coding.app.models.VerificationCode;
import com.coding.app.models.enums.EmailType;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import com.coding.app.repository.VerificationCodeRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationCodeRepository verificationCodeRepository;

    public void registerUser(final User user, HttpServletRequest request) throws InvalidObjectException {

        final HashMap<String, String> errors = Utils.validate(user);
        if (errors.isEmpty()) {
            user.setEnabled(true);
            user.addRole(ServerRole.CLIENT);
            final String temporalPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            final VerificationCode verificationCode = createConfirmationCode(user, request);
            user.setEmailVerificationCode(verificationCode);
            userRepository.save(user);
            verificationCodeRepository.save(verificationCode);
            sendAsyncEmail("","","");
            final Authentication authentication =  authenticationManager
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

    public void sendAsyncEmail(String to, String subject, String body) {
        /*final Thread emailSend = new Thread(new Runnable() {

            @Override
            public void run() {
                String to, String body, String topic, String name, EmailType type
                final EmailService.Email email = new EmailService.Email(to,
                        "Your confirmation code is " + emailVerificationCode.getCode(), "Verification to your account",
                        user.getUsername());
                try {
                    emailService.sendEmail(htmlMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        });
        emailSend.start();*/
        //model = new ModelAndView(REDIRECT_HOME);
    }

    // Privates Functions
    private String generateCode() {
        return String.valueOf(new Random().nextInt(9999));
    }
}
