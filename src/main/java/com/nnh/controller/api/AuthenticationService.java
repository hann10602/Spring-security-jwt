package com.nnh.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnh.entity.RoleEntity;
import com.nnh.entity.UserEntity;
import com.nnh.repository.RoleRepository;
import com.nnh.repository.UserRepository;
import com.nnh.service.JwtService;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private RoleRepository roleRep;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationResponse register(RegisterRequest request) {
		List<RoleEntity> authorities = new ArrayList<>();
		authorities.add(roleRep.findOneByCode("ROLE_USER"));
		UserEntity user = new UserEntity();
			user.setUsername(request.getUsername());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setActive(true);
			user.setRoles(authorities);
		userRep.save(user);
		String jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserEntity user = userRep.findByUsername(request.getUsername()).get();
		String jwtToken = jwtService.generateToken(user);
		
		return new AuthenticationResponse(jwtToken);
		
	}
}
