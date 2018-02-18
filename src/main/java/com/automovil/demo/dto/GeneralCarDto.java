package com.automovil.demo.dto;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class GeneralCarDto {

//	@NotNull
//	@Size(min = 3, max = 50)
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

	public GeneralCarDto() {
		
	}
	
	public GeneralCarDto(String name, String description, String varianModel, Integer variantModelId,
			List<Integer> optionalId, Float totalPrice) {
		super();
		this.name = name;
		this.description = description;
		this.varianModel = varianModel;
		this.variantModelId = variantModelId;
		this.optionalId = optionalId;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "GeneralCarDto [name=" + name + ", description=" + description + ", varianModel=" + varianModel
				+ ", variantModelId=" + variantModelId + ", optionalId=" + optionalId + ", totalPrice=" + totalPrice
				+ "]";
	}
	
	
}
