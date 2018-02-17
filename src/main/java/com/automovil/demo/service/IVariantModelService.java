package com.automovil.demo.service;

import java.util.List;

import com.automovil.demo.entity.VariantModel;
import com.automovil.demo.exception.ServiceException;

public interface IVariantModelService {

	public VariantModel getVariantModel(Integer variantModelId) throws ServiceException;
	
	public List<VariantModel> getVariantModelList () throws ServiceException;
}
