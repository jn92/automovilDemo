package com.automovil.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.automovil.demo.entity.Optional;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.repository.OptionalRepository;

@Service("optionalService")
public class OptionalService implements IOptionalService {

	private static final Log LOG = LogFactory.getLog(OptionalService.class);

	@Autowired
	@Qualifier("optionalRepository")
	private OptionalRepository optionalRepository;

	@Override
	public Optional getOptional(Integer optionalId) throws ServiceException {
		Optional optional = optionalRepository.findByIdAndDateDelete(optionalId, null);
		if (optional == null)
			throw new ServiceException("Error al obtener los datos opcional", null);
		return optional;
	}

	@Override
	public List<Optional> optionalList() throws ServiceException {
		List<Optional> optionalList = new ArrayList<Optional>();
		try {
			optionalList = optionalRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException("Error al obtener la lista de Opcionales: ", e.getMessage());
		}
		return optionalList;
	}

	@Override
	public Boolean getOptionalExist(Integer optionalId) throws ServiceException {
		Optional optional = optionalRepository.findByIdAndDateDelete(optionalId, null);
		if (optional == null)
			return false;
		return true;
	}
}
