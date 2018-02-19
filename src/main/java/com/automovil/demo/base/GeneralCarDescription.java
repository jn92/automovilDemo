package com.automovil.demo.base;

import javax.persistence.Column;

public abstract class GeneralCarDescription extends ModelCar{

	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="description", nullable = false, length = 150)
	private String description;
	
	@Column(name="price", nullable = false, length = 50)
	private String price;

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
