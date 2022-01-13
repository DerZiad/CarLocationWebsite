package com.coding.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.coding.app.models.enums.ServerRole;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userPrincipalDetailsService;

	private final static String PRIVATE_REMEMBER_KEY = "hellofriendimsmookerzz";
	private final static int DELAI = 24 * 3600;

	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
		}).and().authenticationProvider(authenticationProvider()).authorizeRequests().antMatchers("/*").permitAll()
				.antMatchers("/signup").permitAll()
				.antMatchers(ServerRole.MANAGER.getSpace() + "/*").hasRole(ServerRole.ADMIN.getRole())
				.antMatchers(ServerRole.ADMIN.getSpace() + "/*").hasRole(ServerRole.ADMIN.getRole())
				.antMatchers("/").hasRole(ServerRole.CLIENT.getRole())
				.antMatchers("/verification").authenticated()
				.antMatchers("/logout").authenticated().anyRequest().permitAll().and().formLogin()
				.loginProcessingUrl("/login").permitAll().loginPage("/login").permitAll()
				.successHandler(mySimpleUrlAuthenticationHandler()).failureUrl("/login?error=true").failureHandler(null)
				.usernameParameter("username").passwordParameter("password").and().logout().deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().rememberMe()
				.tokenValiditySeconds(DELAI).key(PRIVATE_REMEMBER_KEY).rememberMeParameter("rememberme")
				.userDetailsService(userPrincipalDetailsService);
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MySimpleUrlAuthenticationHandler mySimpleUrlAuthenticationHandler() {
		return new MySimpleUrlAuthenticationHandler();
	}
}