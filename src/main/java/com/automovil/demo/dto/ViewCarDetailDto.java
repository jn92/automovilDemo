package com.automovil.demo.dto;

import java.util.List;

public class ViewCarDetailDto extends GeneralCarDto {

	private Integer id;

	private List<OptionalDto> optional;
	private List<VariantModelDto> variantModel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OptionalDto> getOptional() {
		return optional;
	}

	public void setOptional(List<OptionalDto> optional) {
		this.optional = optional;
	}

	public List<VariantModelDto> getVariantModel() {
		return variantModel;
	}

	public void setVariantModel(List<VariantModelDto> variantModel) {
		this.variantModel = variantModel;
	}
}
