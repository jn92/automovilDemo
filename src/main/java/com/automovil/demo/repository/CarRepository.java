package com.automovil.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automovil.demo.entity.Car;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Integer>{

	public Car findByIdAndDateDelete(Integer id, Date dateDelete);
	
//	public List<Car> findAllDateDelete(Date dateDelete);
	
}