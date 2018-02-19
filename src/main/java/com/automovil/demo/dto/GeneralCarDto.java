package com.automovil.demo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class GeneralCarDto {

	private Integer id;

	// @NotNull
	// @Size(min = 3, max = 50)
	private String name;

	private String description;

	private String varianModel;

	private Float varianModelPrice;
	
	private Integer variantModelId;

	private List<Integer> optionalId;

	private List<OptionalDto> optionalListId = new ArrayList<OptionalDto>();
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GeneralCarDto() {

	}

	public GeneralCarDto(Integer id, String name, String description, String varianModel, Float varianModelPrice,
			Integer variantModelId, List<Integer> optionalId, List<OptionalDto> optionalListId, Float totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.varianModel = varianModel;
		this.varianModelPrice = varianModelPrice;
		this.variantModelId = variantModelId;
		this.optionalId = optionalId;
		this.optionalListId = optionalListId;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "GeneralCarDto [id=" + id + ", name=" + name + ", description=" + description + ", varianModel="
				+ varianModel + ", varianModelPrice=" + varianModelPrice + ", variantModelId=" + variantModelId
				+ ", optionalId=" + optionalId + ", optionalListId=" + optionalListId + ", totalPrice=" + totalPrice
				+ "]";
	}

	public Float getVarianModelPrice() {
		return varianModelPrice;
	}

	public void setVarianModelPrice(Float varianModelPrice) {
		this.varianModelPrice = varianModelPrice;
	}

	public List<OptionalDto> getOptionalListId() {
		return optionalListId;
	}

	public void setOptionalListId(List<OptionalDto> optionalListId) {
		this.optionalListId = optionalListId;
	}

}
