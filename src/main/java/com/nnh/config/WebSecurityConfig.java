package com.nnh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.nnh.service.CustomAuthenticationSuccessHandler;
import com.nnh.service.CustomLogoutHandler;
import com.nnh.service.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean 
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//		.antMatchers("/", "/login2", "/auth").permitAll()
//		.antMatchers("/public").hasAnyRole("USER", "ADMIN")
//		.antMatchers("/user1", "/user2").hasRole("USER")
//		.antMatchers("/moder").hasRole("MODER")
//		.antMatchers("/admin").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().failureUrl("/login").successHandler(authenticationSuccessHandler());
//	}
	 
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication()
//		.withUser("user123")
//		.password(passwordEncoder().encode("user123"))
//		.roles("USER")
//		.and().withUser("admin123")
//		.password(passwordEncoder().encode("admin123"))
//		.roles("ADMIN").authorities("READ")
//		.and().withUser("moder123")
//		.password(passwordEncoder().encode("moder123"))
//		.roles("MODER");
//	}
}
