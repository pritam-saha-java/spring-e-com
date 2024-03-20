package com.pritam.controller;

import com.pritam.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.entity.UserEntity;
import com.pritam.service.UserService;

@RestController
public class AuthenticationController {

	private final UserService customerService;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository customerRepository;

    public AuthenticationController(UserService customerService,
									PasswordEncoder passwordEncoder,
									UserRepository customerRepository) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
	public ResponseEntity<UserEntity> saveCustomerHandler(@RequestBody UserEntity customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		UserEntity registeredCustomer = customerService.registerCustomer(customer);
		return new ResponseEntity<>(registeredCustomer, HttpStatus.ACCEPTED);
	}

	@GetMapping("/signIn")
	public ResponseEntity<UserEntity> CustomerLoginDetailsHandler(Authentication auth) {
		UserEntity customer = customerRepository.findByEmail(auth.getName())
				.orElseThrow(() -> new BadCredentialsException("Invalid Username password"));
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}
}
