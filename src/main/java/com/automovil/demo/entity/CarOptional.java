package com.automovil.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.automovil.demo.base.ModelCar;

@Entity
@Table(name = "car_optional")
public class CarOptional extends ModelCar {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "optional_id", nullable = true, referencedColumnName = "id")
	private Optional optional;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", nullable = true, referencedColumnName = "id")
	private Car car;

	public CarOptional() {
	}

	public CarOptional(Optional optional, Car car) {
		super();
		this.optional = optional;
		this.car = car;
	}

	public Optional getOptional() {
		return optional;
	}

	public void setOptional(Optional optional) {
		this.optional = optional;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
