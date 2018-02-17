package com.automovil.demo.service;

import java.util.List;

import com.automovil.demo.dto.CreateCarDto;
import com.automovil.demo.dto.UpdateCarDto;
import com.automovil.demo.dto.ViewCarDetailDto;
import com.automovil.demo.dto.ViewCarDto;
import com.automovil.demo.exception.ServiceException;

public interface ICarService {

	public void CreateCar (CreateCarDto dto) throws ServiceException;
	
	public void UpdateCar (UpdateCarDto dto) throws ServiceException;
	
	public void DeleteCar (Integer carId) throws ServiceException;
	
	public ViewCarDetailDto ViewCarDetail (Integer carId) throws ServiceException;
	
	public List<ViewCarDto> ViewCarList () throws ServiceException;
}

