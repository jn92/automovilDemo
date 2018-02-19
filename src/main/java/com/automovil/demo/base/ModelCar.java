package com.automovil.demo.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;

@MappedSuperclass
public abstract class ModelCar  implements Identifiable<Integer>{
	
	/**
	 * CLASE BASE DE DOMINIO
	 */
//	strategy = GenerationType.AUTO
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_insert", nullable = false, updatable = false)
    private Date dateInsert;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update", nullable = true)
    private Date dateUpdate;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_delete", nullable = true)
    private Date dateDelete;
	
	@Column(name="version", nullable = false, updatable = true)
	private Integer version;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	/**
	 * Inserta la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createOnRepository(JpaRepository repository) {
		this.dateInsert = new Date();
		this.version = 0;
		repository.save(this);
	}

	
	/**
	 * Actualiza la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateOnRepository(JpaRepository repository) {
		this.dateUpdate = new Date();
		this.version++;
		repository.save(this);
	}

	
	/**
	 * Elimina la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteOnRepository(JpaRepository repository) {
		this.dateDelete = new Date();
		this.version++;
		repository.save(this);
	}

	
	/**
	 * Elimina la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteOnRepository(JpaRepository repository, Boolean physicalDelete) {
		this.dateDelete = new Date();
		this.version++;
		if(physicalDelete)
			repository.delete(this);
		else
			repository.save(this);
	}
}