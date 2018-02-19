package com.automovil.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.automovil.demo.dto.GeneralCarDto;
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
	
	public Car converterCarCreate (GeneralCarDto generalCarDto) throws ServiceException {
		Car car = new Car();
		try {
			Float price = (float) 0;
			car.setName(generalCarDto.getName());
			car.setDescription(generalCarDto.getDescription());
			car.setVariantModel(variantModelService.getVariantModelByName(generalCarDto.getVarianModel()));
			price = price + car.getVariantModel().getPrice();
			for (Integer optional : generalCarDto.getOptionalId()) {
				Optional optionalEntity = optionalService.getOptional(optional);
				price =price + optionalEntity.getPrice();
			}
			car.setPrice(price);
			
		} catch (ServiceException e) {
			throw new ServiceException("Se produjo un error al realizar el pasaje de datos a la entidad.", "Error:" + e);
		}
		return car;
	}

}
