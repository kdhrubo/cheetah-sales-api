package com.cheetahapps.sales;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import com.cheetahapps.sales.security.MultiTenantNimbusJwtDecoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;

	private static final String[] AUTHENTICATION_WHITELIST = { 
			"/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**",
	        "/users/provision"
	};

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().ignoringAntMatchers(AUTHENTICATION_WHITELIST)
				// .and()
				// .exceptionHandling()
				// .authenticationEntryPoint(problemSupport)
				// .accessDeniedHandler(problemSupport)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(AUTHENTICATION_WHITELIST).permitAll().and().authorizeRequests()
				.anyRequest().authenticated().and().oauth2ResourceServer()
				// .authenticationEntryPoint(problemSupport)
				// .accessDeniedHandler(problemSupport)
				.jwt();
		// @formatter:on
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
	    return MultiTenantNimbusJwtDecoder.withJwkSetUri(this.jwkSetUri); //TODO fix code design
	}

}
