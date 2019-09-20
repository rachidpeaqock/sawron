package com.tenor.tsf.gs.dao;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.tenor.tsf.gs.db.MaterielDB;
import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class MaterielDao implements AbstractDAO<Materiel> {
	private static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	private static Long count=0l;
	public Materiel create(Materiel mat) throws EmptyFieldException {

		Validate.notNull(mat, "null");
		Materiel materiel = null;
		LOGGER.info(materiel);
		materiel = findById(mat.getId());
		if (materiel != null) {
			throw new AllreadyExistException("materiel "+mat.getId()+" is allready existe");
		}
		if (mat.getLibelle() == "") {
			throw new EmptyFieldException("Mat libelle is empty");
		}
		count += 1L;
		mat.setId(count);
		MaterielDB.materiels.add(mat);
		materiel = this.findById(mat.getId());
		LOGGER.info(materiel);
		return materiel;

	}

	public void update(Materiel materiel) {
		Validate.notNull(materiel, "object given is null");
		Materiel mat = findById(materiel.getId());
		LOGGER.info(mat);

		if (mat == null) {
			throw new NotFoundException("materiel "+materiel.getId()+" not found");
		}
		int index = MaterielDB.materiels.indexOf(mat);
		MaterielDB.materiels.set(index, materiel);
		mat = findById(materiel.getId());
		LOGGER.info(mat);

	}

	public void delete(Materiel materiel) {
		Validate.notNull(materiel, "Object given is null");
		Materiel mat = null;
		LOGGER.info(mat);

		mat = this.findById(materiel.getId());
		LOGGER.info(materiel);
		if (mat == null) {
			throw new NotFoundException("materiel "+materiel.getId()+" not found");
		}
		MaterielDB.materiels.remove(materiel);

	}

	public List<Materiel> getAll() {
		LOGGER.info( MaterielDB.materiels);
		return MaterielDB.materiels;

	}

	public Materiel getByName(String name) {
		Materiel materiel = null;
		for (int i = 0; i < MaterielDB.materiels.size(); i++) {

			if (MaterielDB.materiels.get(i).getLibelle() == name) {

				materiel = MaterielDB.materiels.get(i);

			}

		}
		//LOGGER.info( materiel);
		return materiel;
	}

	public Materiel findById(Long id) {
		Materiel materiel = null;
		for (int i = 0; i < MaterielDB.materiels.size(); i++) {

			if (MaterielDB.materiels.get(i).getId() == id) {

				materiel = MaterielDB.materiels.get(i);
			}

		}
		//LOGGER.info( materiel);
		return materiel;

	}
	public Materiel findBySalleId(Long id) {
		Materiel materiel = null;
		for (int i = 0; i < MaterielDB.materiels.size(); i++) {
			
			if (MaterielDB.materiels.get(i).getSalle().getId() == id) {
				
				materiel = MaterielDB.materiels.get(i);
			}
			
		}
		//LOGGER.info( materiel);
		return materiel;
		
	}
}
