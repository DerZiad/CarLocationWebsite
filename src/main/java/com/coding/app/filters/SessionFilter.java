package com.coding.app.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;

@Order(1)
public class SessionFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean testRessource = false;
		List<String> list = Arrays.asList("/assets", "/client", "/delibdesign");

		for (String item : list) {
			if (path.startsWith(item)) {
				testRessource = true;
			}
		}

		/**
		 * 
		 * I did if else and not else if because i doesn t to do a long traitment for
		 * ressoucres
		 **/

		if (testRessource) {
			chain.doFilter(req, resp);
		} else {
			boolean testActions = false;
			List<String> allowedLink = Arrays.asList("/login", "/signup");
			for (String item : allowedLink) {
				if (path.startsWith(item))
					testActions = true;
			}

			if (testActions) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					chain.doFilter(request, response);
				} else {
					resp.sendRedirect("/");
				}

			} else {
				if (path.startsWith("/logout")) {
					if (authentication instanceof AnonymousAuthenticationToken) {
						resp.sendRedirect("/");
					} else {
						chain.doFilter(request, response);
					}
				} else if (path.equals("/")) {
					Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
					Iterator iterator = authorities.iterator();
					GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
					if (grantedAuthority.getAuthority().equals("ROLE_" + ServerRole.ADMIN.getRole())) {
						resp.sendRedirect("/admin");
					} else if (grantedAuthority.getAuthority().equals("ROLE_" + ServerRole.MANAGER.getRole())) {
						resp.sendRedirect("/manager");
					} else {
						if (!(authentication instanceof AnonymousAuthenticationToken)) {
							User user = (User) authentication.getPrincipal();
							if (!user.isVerificated()) {
								resp.sendRedirect("/verification");
							} else {
								chain.doFilter(request, response);
							}
						} else {
							chain.doFilter(request, response);
						}
					}
				} else if (path.equals("/admin")) {
					if (authentication instanceof AnonymousAuthenticationToken) {
						resp.sendRedirect("/login");
					} else {
						Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
						Iterator iterator = authorities.iterator();
						GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
						if (grantedAuthority.getAuthority().equals("ROLE_" + ServerRole.ADMIN.getRole())) {
							chain.doFilter(request, response);
						} else {
							resp.sendRedirect("/");
						}
					}

				} else {
					chain.doFilter(request, response);
				}
			}
		}

	}
}
