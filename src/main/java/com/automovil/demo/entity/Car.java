package com.automovil.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.automovil.demo.base.ModelCar;

@Entity
@Table(name = "car")
public class Car extends ModelCar {

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = false, length = 50)
	private String description;

	@Column(name = "price", nullable = false)
	private Float price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "variant_model_id", nullable = true, referencedColumnName = "id")
	private VariantModel variantModel;

	public Car() {
	}

	public Car(String name, String description, Float price, VariantModel variantModel) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.variantModel = variantModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VariantModel getVariantModel() {
		return variantModel;
	}

	public void setVariantModel(VariantModel variantModel) {
		this.variantModel = variantModel;
	}

}
