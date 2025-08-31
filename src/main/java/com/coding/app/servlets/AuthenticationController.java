package com.coding.app.servlets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.coding.app.models.User;
import com.coding.app.models.Verification;
import com.coding.app.models.enums.CodeType;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import com.coding.app.repository.VerificationRepository;
import com.coding.app.services.EmailConfirmation;
import com.coding.app.services.EmailService;
import com.coding.app.services.HtmlMessage;

@Controller
public class AuthenticationController {

	private static final String ATTRIBUT_LOGIN = "isLogin";

	private static final String PATH_LOGIN = "auth/login";
	private static final String PATH_SIGNUP = "auth/signup";
	private static final String PATH_VERIFICATION = "auth/verification";
	
	private static final String REDIRECT_HOME = "redirect:/";

	private static final String ATTRIBUT_ERROR = "error";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationRepository verificationRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public ModelAndView getPageLoginAdmin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(PATH_LOGIN);
		model.addObject(ATTRIBUT_LOGIN, true);
		String err = request.getParameter(ATTRIBUT_ERROR);
		if (err != null)
			model.addObject(ATTRIBUT_ERROR, "Vos identifiants sont incorrects.");
		return model;
	}

	@GetMapping("/signup")
	public ModelAndView getPageSignup(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(PATH_SIGNUP);
		return model;
	}

	@PostMapping("/signup")
	public ModelAndView validSignup(@RequestPayload User user, HttpServletRequest request)
			throws StrangeEvenement, MessagingException {
		ModelAndView model = new ModelAndView(PATH_SIGNUP);
		HashMap<String, String> errors = new HashMap<String, String>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> violatons = validator.validate(user);
		for (ConstraintViolation<User> constraintViolation : violatons) {
			errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		}
		if (errors.size() == 0
				&& user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
				&& user.getUsername().matches(".*\\..*")) {
			Verification verification = new Verification();
			verification.setUser(user);
			verification.setIp(request.getRemoteAddr());
			verification.setType(CodeType.VERIFICATION);
			verification.generateCode();
			user.setEnabled(true);
			user.addRole(ServerRole.CLIENT);
			String temporalPassword = user.getPassword();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setVerificationEmail(verification);
			userRepository.save(user);
			verificationRepository.save(verification);
			
			Authentication authentication =  authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), temporalPassword));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			/**
			 * Multi thread
			 * **/
			
			Thread emailSend = new Thread(new Runnable() {
				
				@Override
				public void run() {
					HtmlMessage htmlMessage = new EmailConfirmation(user.getEmail(),
							"Your confirmation code is " + verification.getCode(), "Verification to your account",
							user.getUsername());
					try {
						emailService.sendEmail(htmlMessage);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					
				}
			});
			emailSend.start();
			model = new ModelAndView(REDIRECT_HOME);
		} else {
			throw new StrangeEvenement("Event fchkel");
		}

		return model;
	}

	@GetMapping("/verification")
	public ModelAndView validSignupGet() {
		ModelAndView model = new ModelAndView(PATH_VERIFICATION);
		return model;
	}

	@PostMapping("/verification")
	public ModelAndView validSignup(@RequestParam("code") String code,
			HttpServletRequest request) throws StrangeEvenement, MessagingException {
		ModelAndView model = new ModelAndView(PATH_VERIFICATION);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Verification verification = user.getVerificationEmail();
		if(verification.isDead()) {
			Verification verificationNew = new Verification();
			verificationNew.setIp(request.getRemoteAddr());
			verificationNew.setType(CodeType.VERIFICATION);
			verificationNew.generateCode();
			user.setVerificationEmail(verificationNew);
			userRepository.save(user);
			verification.setUser(null);
			verificationRepository.delete(verification);
			
			
			/**
			 * Multi thread
			 * **/
			
			Thread emailSend = new Thread(new Runnable() {
				
				@Override
				public void run() {
					HtmlMessage htmlMessage = new EmailConfirmation(user.getEmail(),
							"Your confirmation code is " + verification.getCode(), "Verification to your account",
							user.getUsername());
					try {
						emailService.sendEmail(htmlMessage);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					
				}
			});
			emailSend.start();
			
			String error = "We sent you another code , your code is expired";
			model.addObject(ATTRIBUT_ERROR,error);
		}else {
			String secretCode = verification.getCode();
			if(secretCode.equals(code)) {
				user.setVerificated(true);
				user.setVerificationEmail(null);
				userRepository.save(user);
				verificationRepository.delete(verification);
				model = new ModelAndView(REDIRECT_HOME);
			}else {
				String error = "code invalid";
				model.addObject(ATTRIBUT_ERROR,error);
			}
		}
		
		return model;
	}
}
