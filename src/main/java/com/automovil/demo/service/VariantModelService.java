package com.automovil.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.automovil.demo.entity.VariantModel;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.repository.VariantModelRepository;

@Service("variantModelService")
public class VariantModelService implements IVariantModelService {

	private static final Log LOG = LogFactory.getLog(VariantModelService.class);

	@Autowired
	@Qualifier("variantModelRepository")
	private VariantModelRepository variantModelRepository;

	@Override
	public VariantModel getVariantModel(Integer variantModelId) throws ServiceException {
		VariantModel variantModel = variantModelRepository.findByIdAndDateDelete(variantModelId, null);
		if (variantModel == null)
			throw new ServiceException("Variante de Modelos: ", "Error al obtener los datos de variante de modelo");
		return variantModel;
	}
	
	@Override
	public VariantModel getVariantModelByName(String variantModelName) throws ServiceException {
		VariantModel variantModel = variantModelRepository.findByNameAndDateDelete(variantModelName, null);
		if (variantModel == null)
			throw new ServiceException("Variante de Modelos: ", "Error al obtener los datos de variante de modelo");
		return variantModel;
	}

	@Override
	public List<VariantModel> getVariantModelList() throws ServiceException {
		List<VariantModel> variantModelList = new ArrayList<VariantModel>();
		try {
			variantModelList = variantModelRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException("Variante de Modelos: ", "Error al obtener la lista de Variantes de Modelos");
		}
		return variantModelList;
	}

}
