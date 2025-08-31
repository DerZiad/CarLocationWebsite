package com.coding.app.controllers;

import com.coding.app.exceptions.InvalidObjectException;
import com.coding.app.models.User;
import com.coding.app.repository.UserRepository;
import com.coding.app.repository.VerificationCodeRepository;
import com.coding.app.services.AuthenticationService;
import com.coding.app.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

	private static final String ATTRIBUT_LOGIN = "isLogin";

	private static final String PATH_LOGIN = "auth/login";
	private static final String PATH_SIGNUP = "auth/signup";
	private static final String PATH_VERIFICATION = "auth/verification";
	
	private static final String REDIRECT_HOME = "redirect:/";

	private static final String ATTRIBUT_ERROR = "error";

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final VerificationCodeRepository verificationCodeRepository;

	private final EmailService emailService;

	private final AuthenticationService authenticationService;

	@GetMapping("/login")
	public ModelAndView loginForm(HttpServletRequest request) {
		final ModelAndView model = new ModelAndView(PATH_LOGIN);
		model.addObject(ATTRIBUT_LOGIN, true);
		String err = request.getParameter(ATTRIBUT_ERROR);
		if (err != null)
			model.addObject(ATTRIBUT_ERROR, "Vos identifiants sont incorrects.");
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
		}catch (InvalidObjectException e){
			model.addObject(ATTRIBUT_ERROR, e.getErrors());
			return model;
		}
		return model;
	}

	@GetMapping("/verification")
	public ModelAndView validSignupGet() {
        return new ModelAndView(PATH_VERIFICATION);
	}

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
                        "Your confirmation code is " + verification.getCode(), "Verification to your account",
                        user.getUsername());
                try {
                    emailService.sendEmail(htmlMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            });
			emailSend.start();
			
			String error = "We sent you another code , your code is expired";
			model.addObject(ATTRIBUT_ERROR,error);
		}else {
			String secretCode = verification.getCode();
			if(secretCode.equals(code)) {
				user.setValidated(true);
				user.setEmailVerificationCode(null);
				userRepository.save(user);
				verificationCodeRepository.delete(verification);
				model = new ModelAndView(REDIRECT_HOME);
			}else {
				String error = "code invalid";
				model.addObject(ATTRIBUT_ERROR,error);
			}
		}
		
		return model;
	}*/
}
