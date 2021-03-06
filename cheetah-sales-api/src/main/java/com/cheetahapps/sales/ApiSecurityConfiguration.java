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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import com.cheetahapps.sales.security.MultiTenantNimbusJwtDecoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${app.authz.jwt.issuer}")
	private String issuer;

	private static final String[] AUTHENTICATION_WHITELIST = { "/swagger-resources/**", "/swagger-ui.html",
			"/v2/api-docs", "/webjars/**",  "/users/register" };

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
				.authorizeRequests().antMatchers(AUTHENTICATION_WHITELIST).permitAll().mvcMatchers("/tenants/**")
				.hasAuthority("SCOPE_ROLE_TENANT_ADMIN").anyRequest().authenticated().and().oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
		// @formatter:on
	}

	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return MultiTenantNimbusJwtDecoder.create(this.issuer);
	}

}
