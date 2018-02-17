package com.automovil.demo.service;

import java.util.List;

import com.automovil.demo.entity.Optional;
import com.automovil.demo.exception.ServiceException;

public interface IOptionalService {

	public Optional getOptional(Integer optionalId) throws ServiceException;
	
	public List<Optional> optionalList() throws ServiceException;
	
//	public void delete(Integer optionalId) throws ServiceException;
}
