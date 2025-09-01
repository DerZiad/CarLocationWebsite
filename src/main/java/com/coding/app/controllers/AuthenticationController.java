package com.coding.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.models.User;
import com.coding.app.services.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private static final String ATTR_LOGIN = "isLogin";
    private static final String PATH_LOGIN = "login";
    private static final String PATH_SIGNUP = "signup";
    private static final String PATH_VERIFICATION = "auth/verification";
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_ERRORS = "errors";

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ModelAndView loginForm(HttpServletRequest request) {
        final ModelAndView model = new ModelAndView(PATH_LOGIN);
        model.addObject(ATTR_LOGIN, true);
        final String err = request.getParameter(ATTR_ERROR);
        if (err != null)
            model.addObject(ATTR_ERROR, "Your credentials are incorrect.");
        return model;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView(PATH_SIGNUP);
    }

    @PostMapping("/signup")
    public ModelAndView submitSignupData(@RequestPayload User user, HttpServletRequest request) {
        final ModelAndView model = new ModelAndView(PATH_SIGNUP);
        try {
            authenticationService.registerUser(user, request);
        } catch (InvalidObjectException e) {
            model.addObject(ATTR_ERROR, e.getMessage());
            model.addObject(ATTR_ERRORS, e.getErrors());
            return model;
        }
        return model;
    }

    /*@GetMapping("/verification")
    public ModelAndView validSignupGet(@RequestParam("code") String code, @RequestParam("username") String username) {
        authenticationService.verifyUserCode(username, code);
        return new ModelAndView(PATH_VERIFICATION);
    }*/

    /*
    @PostMapping("/verification")
    public ModelAndView validSignup(@RequestParam("code") String code,
            HttpServletRequest request) throws StrangeEvenement, MessagingException {
        ModelAndView model = new ModelAndView(PATH_VERIFICATION);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VerificationCode verification = user.getEmailVerificationCode();
        if(verification.isDead()) {
            VerificationCode verificationCodeNew = new VerificationCode();
            verificationCodeNew.setIp(request.getRemoteAddr());
            verificationCodeNew.setType(CodeType.VERIFICATION);
            verificationCodeNew.generateCode();
            user.setEmailVerificationCode(verificationCodeNew);
            userRepository.save(user);
            verification.setUser(null);
            verificationCodeRepository.delete(verification);

            Thread emailSend = new Thread(() -> {
                EmailService.Email htmlMessage = new EmailService.Email(user.getEmail(),
                        "Your confirmation code is " + verification.getCode(), "Verification for your account",
                        user.getUsername());
                try {
                    emailService.sendEmail(htmlMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            emailSend.start();

            String error = "We sent you another code, your previous code is expired.";
            model.addObject(ATTR_ERROR, error);
        } else {
            String secretCode = verification.getCode();
            if(secretCode.equals(code)) {
                user.setValidated(true);
                user.setEmailVerificationCode(null);
                userRepository.save(user);
                verificationCodeRepository.delete(verification);
                model = new ModelAndView(REDIRECT_HOME);
            } else {
                String error = "Invalid code.";
                model.addObject(ATTR_ERROR, error);
            }
        }

        return model;
    }
    */
}
