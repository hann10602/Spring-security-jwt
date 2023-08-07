package com.nnh.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Web {
	@GetMapping("/")
	public String getHome() {
		return "Web";
	}
	
//	@GetMapping("/login2")
//	public String getLogin() {
//		return "Login2";
//	}
	
	@GetMapping("/user1")
	public String getUser() {
		return "User1";
	}
	
	@GetMapping("/user2")
	public String getUser2() {
		return "User2";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Admin";
	}
	
	@GetMapping("/public")
	public String getPublic() {
		return "Public";
	}
	
	@GetMapping("/general")
	public String getGeneral() {
		return "General";
	}
	
	@GetMapping("/moder")
	public String getModer() {
		return "Moder";
	}
}
