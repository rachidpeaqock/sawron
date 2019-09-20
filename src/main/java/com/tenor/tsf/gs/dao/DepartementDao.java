package com.tenor.tsf.gs.dao;

import java.util.List;



import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.tenor.tsf.gs.db.DepartementDB;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;
@Configuration
@Component
public class DepartementDao implements AbstractDAO<Departement> {
	private static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	private static Long count = 0l;
	private  SessionFactory sessionFactory ;

	public Departement create(Departement dept) throws AllreadyExistException, EmptyFieldException {

		Validate.notNull(dept, "object given is null");
		Departement departement = getByName(dept.getLibelle());
		LOGGER.info( departement);
		if (departement != null) {
			throw new AllreadyExistException("departemet id: " + dept.getId() + " allready exciste");

		}

		// LOGGER.info(dept);
		if (dept.getLibelle() == "") {
			throw new EmptyFieldException("departement name is empty");
		}

		count += 1L;
		dept.setId(count);
		DepartementDB.departements.add(dept);
		departement = this.getById(dept.getId());
		LOGGER.info(departement);

		return departement;

	}

	public void update(Departement dept) throws NotFoundException {
		Departement departement = getById(dept.getId());
		LOGGER.info( departement);

		if (departement == null) {
			throw new NotFoundException("departement id given not found");
		}
		DepartementDB.departements.set(DepartementDB.departements.indexOf(departement), dept);
		departement = getById(dept.getId());
		LOGGER.info( departement);

	
	}

	public void delete(Departement dept) throws NotFoundException {
		Departement departement = null;
		Validate.notNull(dept, "object given null");
		departement = this.getById(dept.getId());	
		if (departement == null) {
			throw new NotFoundException("Depratement given not found");
		}
		LOGGER.info( departement);
		DepartementDB.departements.remove(departement);

	}

	public List<Departement> getAll() {
		return DepartementDB.departements;
	}

	public Departement getById(Long Id) {
		Departement departement = null;
		for (int i = 0; i < DepartementDB.departements.size(); i++) {
			if (DepartementDB.departements.get(i).getId() == Id) {
				departement = DepartementDB.departements.get(i);

				break;
			}
		}
		LOGGER.info( departement);
		return departement;
	}

	public Departement getByName(String name) {
		// LOGGER.info(Id);
		Departement departement = null;
		for (int i = 0; i < DepartementDB.departements.size(); i++) {
			if (DepartementDB.departements.get(i).getLibelle() == name) {
				departement = DepartementDB.departements.get(i);

				break;
			}
		}
		LOGGER.info( departement);
		return departement;
	}
}
