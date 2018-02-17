package com.automovil.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automovil.demo.entity.VariantModel;

@Repository("variantModelRepository")
public interface VariantModelRepository extends JpaRepository<VariantModel,Integer>{

	public VariantModel findByIdAndDateDelete(Integer id, Date dateDelete);
	
//	public List<VariantModel> findAllDateDelete(Date dateDelete);
}