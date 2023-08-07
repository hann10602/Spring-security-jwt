//package com.nnh.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.nnh.entity.UserEntity;
//import com.nnh.repository.UserRepository;
//
//@Component
//public class MyUserDetailsService implements UserDetailsService{
//	@Autowired
//	UserRepository userRep;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserEntity user = userRep.findByUsername(username).get();
//		
//		return user;
//	}
//	
//	public UserEntity registerUser() {
//        String hashedPassword = new BCryptPasswordEncoder().encode("user789");
//
//        // Save the user to the database with the hashed password
//        UserEntity user = new UserEntity();
//        user.setUsername("user789");
//        user.setPassword(hashedPassword);
//        user.setActive(true);
//        return userRep.save(user);
//    }
//
//}
