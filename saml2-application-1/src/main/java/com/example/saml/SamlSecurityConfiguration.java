package com.example.saml;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SamlSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
        	.authorizeRequests()
        		.anyRequest().authenticated()
        	.and()
        		.saml2Login();
	}
	
}
