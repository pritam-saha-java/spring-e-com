package com.pritam.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pritam.entity.UserEntity;
import com.pritam.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> opt = customerRepository.findByEmail(username);
		if (opt.isPresent()) {
			UserEntity customer = opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), authorities);
		}
		else throw new BadCredentialsException("User Details not found with : " + username);
	}

}
