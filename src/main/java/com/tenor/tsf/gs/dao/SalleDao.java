package com.tenor.tsf.gs.dao;

import java.util.List;


import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenor.tsf.gs.db.SalleDB;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;
@Component
public class SalleDao implements AbstractDAO<Salle> {
	private static Long count=0l;
	private static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	public Salle create(Salle salle) {
		Validate.notNull(salle, "null");
		Salle sal = this.getById(salle.getId());
		if (sal != null) {
			throw new AllreadyExistException("salle "+salle.getId()+" allready exsit");
		}
		if (salle.getLibelle() == "") {
			throw new EmptyFieldException("salle name is empty");
		}
		LOGGER.info( sal);
		count += 1L;
		salle.setId(count);
		SalleDB.salles.add(salle);
		sal=this.getById(salle.getId());
		LOGGER.info( sal);

		return sal;
	}

	public void update(Salle salle) {

		Validate.notNull(salle, "null");
		Salle sal = this.getById(salle.getId());
		if (sal == null) {
			throw new NotFoundException("salle "+salle.getId()+" not found");
		}
		LOGGER.info( sal);
		int index=SalleDB.salles.indexOf(sal);
		SalleDB.salles.set(index, salle);
		sal = this.getById(salle.getId());
		LOGGER.info( sal);

	}

	public void delete(Salle salle) {
		Validate.notNull(salle, "object given is null");
		Salle sal = this.getById(salle.getId());
		if (sal == null) {
			throw new NotFoundException("salle "+salle.getId()+"not found");
		}
		LOGGER.info( sal);
		SalleDB.salles.remove(sal);
	}

	public List<Salle> getAll() {
		return SalleDB.salles;
	}

	public Salle getById(Long Id) {
		Salle salle = null;
		for (Salle sal : SalleDB.salles) {
			if (sal.getId() == Id) {
				salle = sal;

			}
		}
		LOGGER.info( salle);
		return salle;
	}
	public Salle getByName(String name) {
		Salle salle = null;
		for (Salle sal : SalleDB.salles) {
			if (sal.getLibelle() == name) {
				salle = sal;
			}
		}
		LOGGER.info( salle);
		return salle;
	}
}