package com.automovil.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automovil.demo.component.CarConverter;
import com.automovil.demo.dto.GeneralCarDto;
import com.automovil.demo.dto.OptionalDto;
import com.automovil.demo.dto.ViewCarDetailDto;
import com.automovil.demo.dto.ViewCarDto;
import com.automovil.demo.entity.Car;
import com.automovil.demo.entity.CarOptional;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.repository.CarRepository;

@Service("carService")
public class CarService implements ICarService{

	private static final Log LOG = LogFactory.getLog(CarService.class);
	
	@Autowired
	@Qualifier("carRepository")
	private CarRepository carRepository;
	
	@Autowired
	@Qualifier("optionalService")
	private OptionalService optionalService;
	
	@Autowired
	@Qualifier("variantModelService")
	private VariantModelService variantModelService;
	
	@Autowired
	@Qualifier("carOptionalService") 
	private CarOptionalService carOptionalService;
	
	@Autowired
	@Qualifier("carConverter")
	private CarConverter carConverter;
	
	@Transactional
	public void CreateCar (GeneralCarDto generalCarDto) throws ServiceException{
		Car car = carConverter.converterCarCreate(generalCarDto);
		car.createOnRepository(carRepository);
		for (Integer optionalId : generalCarDto.getOptionalId()) {
			CarOptional carOptional = new CarOptional();
			carOptional.setOptional(optionalService.getOptional(optionalId));
			carOptional.setCar(car);
			carOptionalService.create(carOptional);
		}
	}

	@Override
	@Transactional
	public void UpdateCar(GeneralCarDto dto) throws ServiceException {
		Car car = getCar(dto.getId());
		if(car == null)
			throw new ServiceException("Error al realizar la modificacion sobre el automovil seleccionado", null);
		car.setName(dto.getName());
		car.setVariantModel(variantModelService.getVariantModelByName(dto.getVarianModel()));
		List<CarOptional> carOptionals = carOptionalService.carOptionalList(car);
		deleteItemsOptional(carOptionals, dto.getOptionalId());
		addItemsOptional(carOptionals, dto.getOptionalId());
		car.setPrice(dto.getTotalPrice());
		car.updateOnRepository(carRepository);		
	}

	@Override
	@Transactional
	public void DeleteCar(Integer carId) throws ServiceException {
		Car car = new Car();
		try {			
			car = getCar(carId);
			car.deleteOnRepository(carRepository);
		} catch (Exception e) {
			throw new ServiceException("Error al eliminar el Automovil seleccionado.", null);
		}
	}

	@Override
	@Transactional
	public GeneralCarDto ViewCarDetail(Integer carId) throws ServiceException {
		GeneralCarDto generalCarDto = new GeneralCarDto();
		Car car = getCar(carId);
		generalCarDto.setId(car.getId());
		generalCarDto.setName(car.getName());
		generalCarDto.setDescription(car.getDescription());
		generalCarDto.setVarianModelPrice(car.getVariantModel().getPrice());
		generalCarDto.setVariantModelId(car.getVariantModel().getId());
		generalCarDto.setVarianModel(car.getVariantModel().getName());
		generalCarDto.setTotalPrice(car.getPrice());
		List<OptionalDto> optionalList = new ArrayList<OptionalDto>();
		List<CarOptional> carOptionals = carOptionalService.carOptionalList(car);
		for (CarOptional carOptional : carOptionals) {
			OptionalDto optional = new OptionalDto();
			optional.setId(carOptional.getOptional().getId());
			optional.setName(carOptional.getOptional().getName());
			optional.setPrice(carOptional.getOptional().getPrice());
			optionalList.add(optional);
		}
		generalCarDto.setOptionalListId(optionalList);
		return generalCarDto;
	}
	
	@Override
	@Transactional
	public List<ViewCarDto> ViewCarList() throws ServiceException {
		List<ViewCarDto> viewCarList = new ArrayList<ViewCarDto>();
		try {
			List<Car> cars = carRepository.findAllByDateDelete(null);
				for (Car car : cars) {
					ViewCarDto viewCarDto = new ViewCarDto();
					viewCarDto.setId(car.getId());
					viewCarDto.setName(car.getName());
					viewCarDto.setVariantModel(car.getVariantModel().getName());
					String optionalName = "";
					List<CarOptional> carOptionals =carOptionalService.carOptionalList(car);
					for (CarOptional carOptional : carOptionals) {
						optionalName = optionalName + " - " + carOptional.getOptional().getName();
					}
					viewCarDto.setPrice(car.getPrice());
					viewCarDto.setOptional(optionalName);
					viewCarList.add(viewCarDto);
				}
		} catch (Exception e) {
			throw new ServiceException("Error al obtener los datos: ", e.getMessage());
		}
		return viewCarList;
	}
	
	@Transactional
	protected Car getCar(Integer carId)  {
		return carRepository.findByIdAndDateDelete(carId, null);
	}
	
	@Transactional
	protected void deleteItemsOptional(List<CarOptional> carOptionals, List<Integer> optionalIds) throws ServiceException {
		for (CarOptional carOptional : carOptionals) {
			Boolean exist = false;
			for (Integer optionalId : optionalIds) {
				if(carOptional.getOptional().getId().equals(optionalId))
					exist = true;
			}
			if(!exist)
				carOptionalService.delete(carOptional.getCar(), carOptional.getOptional());
		}
	}
	
	@Transactional
	protected void addItemsOptional(List<CarOptional> carOptionals, List<Integer> optionalIds) throws ServiceException {
		for (Integer optionalId : optionalIds) {
			CarOptional carOptionalEntity = new CarOptional();
			Boolean exist = false;
			for (CarOptional carOptional : carOptionals) {
					if(carOptional.getOptional().getId().equals(optionalId)) {						
						exist = true;
					}else {
						carOptionalEntity.setCar(carOptional.getCar());
					}
			}
			if(!exist) {
				carOptionalEntity.setOptional(optionalService.getOptional(optionalId));
				carOptionalService.create(carOptionalEntity);
			}
		}
	}
}