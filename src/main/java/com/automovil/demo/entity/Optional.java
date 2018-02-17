package com.automovil.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.automovil.demo.base.ModelCar;

@Entity
@Table(name = "optional")
public class Optional extends ModelCar {

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = false, length = 150)
	private String description;

	@Column(name = "price", nullable = false, length = 50)
	private String price;

	public Optional() {
	}

	public Optional(String name, String description, String price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
