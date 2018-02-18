package com.automovil.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.automovil.demo.repository.CarOptionalRepository;
import com.automovil.demo.repository.CarRepository;
import com.automovil.demo.repository.OptionalRepository;
import com.automovil.demo.repository.VariantModelRepository;

@Component("genericComponent")
public class GenericComponent {

	@Autowired
	@Qualifier("carRepository")
	private CarRepository carRepository;

	@Autowired
	@Qualifier("carOptionalRepository")
	private CarOptionalRepository carOptionalRepository;

	@Autowired
	@Qualifier("variantModelRepository")
	private VariantModelRepository variantModelRepository;

	@Autowired
	@Qualifier("optionalRepository")
	private OptionalRepository optionalRepository;
}
