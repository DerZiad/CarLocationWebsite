package com.coding.app.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<SessionFilter> loggingFilter() {
		FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<SessionFilter>();

		registrationBean.setFilter(new SessionFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}

}
