package com.example.session19.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.session19.security.CustomAccessDeniedHandler;
import com.example.session19.security.JWTAuthFilter;
import com.example.session19.service.UserDetail;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value = "enable.reactive", havingValue = "false")
public class SecurityConfig {

	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final UserDetail userDetail;
	private final JWTAuthFilter jwtAuthFilter;

	@Autowired
	public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler, UserDetail userDetail,
			JWTAuthFilter jwtAuthFilter) {
		this.customAccessDeniedHandler = customAccessDeniedHandler;
		this.userDetail = userDetail;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/authenticated").hasRole("ADMIN")
						.requestMatchers("/home", "/login", "/register").permitAll()
						.anyRequest().authenticated())
				.userDetailsService(userDetail)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.accessDeniedHandler(customAccessDeniedHandler));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	static RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}

}
