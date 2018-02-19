package com.automovil.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.automovil.demo.entity.Car;
import com.automovil.demo.entity.CarOptional;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.repository.CarOptionalRepository;

@Service("carOptionalService")
public class CarOptionalService implements ICarOptionalService {

	private static final Log LOG = LogFactory.getLog(CarOptionalService.class);

	@Autowired
	@Qualifier("carOptionalRepository")
	private CarOptionalRepository carOptionalRepository;

	public void create(CarOptional carOptional) throws ServiceException {
		try {
			carOptional.createOnRepository(carOptionalRepository);
		} catch (Exception e) {
			throw new ServiceException("Error al Crear Automovil: ", e.getMessage());
		}
	}

	public void update(CarOptional variantModelOptional) throws ServiceException {
		try {
			variantModelOptional.updateOnRepository(carOptionalRepository);
		} catch (Exception e) {
			throw new ServiceException("Error al Crear Automovil: ", e.getMessage());
		}
	}

	@Override
	public List<CarOptional> carOptionalList(Car car) throws ServiceException {
		List<CarOptional> variantModelOptionalList = new ArrayList<CarOptional>();
		try {
			variantModelOptionalList = carOptionalRepository.findAllByCarAndDateDelete(car, null);
		} catch (Exception e) {
			throw new ServiceException("Fallo en la Busqueda de Automoviles", e.getMessage());
		}
		return variantModelOptionalList;
	}

	@Override
	public void delete(Integer carId, Integer optionalId) throws ServiceException {
		CarOptional variantModelOptional = carOptionalRepository.findByCarAndOptionalAndDateDelete(carId, optionalId,
				null);
		variantModelOptional.deleteOnRepository(carOptionalRepository);
	}
}
