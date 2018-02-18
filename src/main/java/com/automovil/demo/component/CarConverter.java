package com.automovil.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.automovil.demo.dto.CreateCarDto;
import com.automovil.demo.entity.Car;
import com.automovil.demo.entity.Optional;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.service.OptionalService;
import com.automovil.demo.service.VariantModelService;

@Component("carConverter")
public class CarConverter {
	
	@Autowired
	@Qualifier("variantModelService")
	private VariantModelService variantModelService;
	
	@Autowired
	@Qualifier("optionalService")
	private OptionalService optionalService;
	
	public Car converterCarCreate (CreateCarDto createCarDto) throws ServiceException {
		Car car = new Car();
		Float price = null;
		car.setName(createCarDto.getName());
		car.setDescription(createCarDto.getDescription());
		car.setVariantModel(variantModelService.getVariantModel(createCarDto.getVariantModelId()));
		for (Integer optional : createCarDto.getOptionalId()) {
			Optional optionalEntity = optionalService.getOptional(optional);
			price =price + Float.parseFloat(optionalEntity.getPrice());
		}
		car.setPrice(price);
		return car;
	}

}
