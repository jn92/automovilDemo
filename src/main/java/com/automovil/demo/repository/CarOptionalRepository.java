package com.automovil.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automovil.demo.entity.Car;
import com.automovil.demo.entity.CarOptional;

@Repository("carOptionalRepository")
public interface CarOptionalRepository extends JpaRepository<CarOptional, Integer> {

	public List<CarOptional> findAllByCarAndDateDelete(Integer carId, Date dateDelete);

	public List<CarOptional> findAllByCarAndDateDelete(Car carId, Date dateDelete);
	
	public CarOptional findByCarAndOptionalAndDateDelete(Integer carId, Integer optionalId, Date dateDelete);
}