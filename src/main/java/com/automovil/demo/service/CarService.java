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
import com.automovil.demo.dto.CreateCarDto;
import com.automovil.demo.dto.OptionalDto;
import com.automovil.demo.dto.UpdateCarDto;
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
	public void CreateCar (CreateCarDto createCarDto) throws ServiceException{
		Car car = carConverter.converterCarCreate(createCarDto);
		car.createOnRepository(carRepository);
		for (Integer optionalId : createCarDto.getOptionalId()) {
			CarOptional carOptional = new CarOptional();
			carOptional.setOptional(optionalService.getOptional(optionalId));
			carOptional.setCar(car);
			carOptionalService.create(carOptional);
		}
	}

	@Override
	@Transactional
	public void UpdateCar(UpdateCarDto dto) throws ServiceException {
		Car car = getCar(dto.getId());
		if(car == null)
			throw new ServiceException("Error al realizar la modificacion sobre el automovil seleccionado", null);
		car.setName(dto.getName());
		car.setVariantModel(variantModelService.getVariantModel(dto.getVariantModelId()));
		List<CarOptional> carOptionals = carOptionalService.carOptionalList(dto.getId());
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
	public ViewCarDetailDto ViewCarDetail(Integer carId) throws ServiceException {

		ViewCarDetailDto dtoResponse = new ViewCarDetailDto();
		Car car = getCar(carId);
		dtoResponse.setDescription(car.getDescription());
		dtoResponse.setName(car.getName());
		dtoResponse.setId(car.getId());
		if(dtoResponse.getVariantModelId().equals(null)) {					
			dtoResponse.setVariantModelId(car.getVariantModel().getId());
			dtoResponse.setVarianModel(car.getVariantModel().getName());
		}
		List<OptionalDto> optionalList = new ArrayList<OptionalDto>();
		List<CarOptional> carOptionals = carOptionalService.carOptionalList(carId);
		for (CarOptional carOptional : carOptionals) {
			if(!carOptional.getDateDelete().equals(null)) {
				OptionalDto optional = new OptionalDto();
				optional.setId(carOptional.getOptional().getId());
				optional.setName(carOptional.getOptional().getName());
				optional.setPrice(Float.parseFloat(carOptional.getOptional().getPrice()));
				optionalList.add(optional);
			}
		}
		return dtoResponse;
	}
	
	@Override
	@Transactional
	public List<ViewCarDto> ViewCarList() throws ServiceException {
		List<ViewCarDto> viewCarList = new ArrayList<ViewCarDto>();
		try {
			List<Car> cars = carRepository.findAll();
//			if(cars.isEmpty()){
//				for (int i = 0; i < 10; i++) {
//					ViewCarDto dto = new ViewCarDto();
//					dto.setId(1);
//					dto.setName("combo");
//					dto.setOptional("aire acondicionado");
//					dto.setVariantModel("coupe");
//					dto.setPrice(Float.parseFloat("250125"));
//					viewCarList.add(dto);
//				}
//			}else {
				for (Car car : cars) {
					ViewCarDto viewCarDto = new ViewCarDto();
					viewCarDto.setId(car.getId());
					viewCarDto.setName(car.getName());
					viewCarDto.setVariantModel(car.getVariantModel().getName());
					String optionalName = null;
					List<CarOptional> carOptionals =carOptionalService.carOptionalList(car.getId());
					for (CarOptional carOptional : carOptionals) {	
						optionalName = optionalName + " " + carOptional.getOptional().getName();
					}
					viewCarDto.setOptional(optionalName);
					viewCarList.add(viewCarDto);
				}
//			}
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
				carOptionalService.delete(carOptional.getCar().getId(), carOptional.getOptional().getId());
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