package com.automovil.demo.service;

import java.util.List;

import com.automovil.demo.dto.GeneralCarDto;
import com.automovil.demo.dto.ViewCarDto;
import com.automovil.demo.exception.ServiceException;

public interface ICarService {

	public void CreateCar(GeneralCarDto dto) throws ServiceException;

	public void UpdateCar(GeneralCarDto dto) throws ServiceException;

	public void DeleteCar(Integer carId) throws ServiceException;

	public GeneralCarDto ViewCarDetail(Integer carId) throws ServiceException;

	public List<ViewCarDto> ViewCarList() throws ServiceException;
}
