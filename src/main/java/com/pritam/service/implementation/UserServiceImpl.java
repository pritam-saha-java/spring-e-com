package com.pritam.service.implementation;

import com.pritam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pritam.exception.CustomerException;
import com.pritam.entity.UserEntity;
import com.pritam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository customerRepository;

	@Override
	public UserEntity registerCustomer(UserEntity customer) throws CustomerException {
		return customerRepository.save(customer);
	}
}
