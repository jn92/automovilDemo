package com.automovil.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.automovil.demo.exception.ServiceException;

public interface IUserService {
	
	public UserDetails loadUserByUserName(String username) throws ServiceException;

}
