package com.nnh.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.nnh.entity.UserEntity;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("USERMODEL", user);
		System.out.println("login");
		
		response.sendRedirect("/");
		
	}

}
