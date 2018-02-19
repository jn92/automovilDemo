package com.automovil.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automovil.demo.entity.Optional;

@Repository("optionalRepository")
public interface OptionalRepository extends JpaRepository<Optional, Integer> {

	public Optional findByIdAndDateDelete(Integer id, Date date);
}