package com.automovil.demo.dto;

public class ViewCarDto {

	private Integer id;

	private String name;

	private String variantModel;

	private String optional;

	private Float price;

	public String getVariantModel() {
		return variantModel;
	}

	public void setVariantModel(String variantModel) {
		this.variantModel = variantModel;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
