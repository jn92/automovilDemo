package com.automovil.demo.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

public abstract class GeneralCarDto {

	@NotNull
	@Size(min=3, max=50)
	private String name;
	
	private String description;
	
	private String varianModel;
	
	@NotNull
	private Integer variantModelId;
	
	private List<Integer> optionalId;
	
	private Float totalPrice;

	public String getVarianModel() {
		return varianModel;
	}

	public void setVarianModel(String varianModel) {
		this.varianModel = varianModel;
	}

	public Integer getVariantModelId() {
		return variantModelId;
	}

	public void setVariantModelId(Integer variantModelId) {
		this.variantModelId = variantModelId;
	}

	public List<Integer> getOptionalId() {
		return optionalId;
	}

	public void setOptionalId(List<Integer> optionalId) {
		this.optionalId = optionalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
