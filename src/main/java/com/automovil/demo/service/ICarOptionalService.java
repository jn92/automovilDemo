package com.automovil.demo.service;

import java.util.List;

import com.automovil.demo.entity.Car;
import com.automovil.demo.entity.CarOptional;
import com.automovil.demo.entity.Optional;
import com.automovil.demo.exception.ServiceException;

public interface ICarOptionalService {

	public void create(CarOptional variantModelOptional) throws ServiceException;

	public List<CarOptional> carOptionalList(Car car) throws ServiceException;

	public void delete(Car car, Optional optional) throws ServiceException;
}
