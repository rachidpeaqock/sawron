package com.tenor.tsf.gs.dao;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.tenor.tsf.gs.db.ReclamationDB;
import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReclamationDao implements AbstractDAO<Reclamation> {
	private static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	private static Long count=0l;
	public Reclamation create(Reclamation recl) {
		Validate.notNull(recl, "obejct given is null");
		Reclamation reclamation = getById(recl.getId());
		LOGGER.info( reclamation);
		if (reclamation != null) 
			throw new AllreadyExistException("Reclamation "+recl.getId()+" is allreday existe");
		if (recl.getMessage() == "")
			throw new EmptyFieldException("reclamation msg empty ");
		if (recl.getSalle() == null)
			throw new EmptyFieldException("reclamation salle empty");
		if (recl.getUser() == null)
			throw new EmptyFieldException("reclamation user empty");
		
		count += 1L;
		recl.setId(count);
		ReclamationDB.reclamations.add(recl);
		reclamation = getById(recl.getId());
		LOGGER.info( reclamation);

		return reclamation;
	}

	public void update(Reclamation recl) {
		Validate.notNull(recl, "object given is null");
		Reclamation reclamation = getById(recl.getId());
		LOGGER.info( reclamation);
		if (reclamation == null)
			throw new NotFoundException("reclamation "+recl.getId()+" not found");
		int index = ReclamationDB.reclamations.indexOf(reclamation);
		ReclamationDB.reclamations.set(index, recl);
		reclamation = getById(recl.getId());
		LOGGER.info( reclamation);
	}

	public void delete(Reclamation recl) {
		Validate.notNull(recl, "object given is null");
		Reclamation reclamation = getById(recl.getId());
		if (reclamation == null)
			throw new NotFoundException("reclamation "+recl.getId()+" not found");
		LOGGER.info( reclamation);
		ReclamationDB.reclamations.remove(reclamation);

	}

	public List<Reclamation> getAll() {
		return ReclamationDB.reclamations;
	}

	public Reclamation getById(Long id) {
		Reclamation reclamation = null;
		for (Reclamation rec : ReclamationDB.reclamations) {
			if (rec.getId() == id) {
				reclamation = rec;

			}
		}
		return reclamation;
	}

	public Reclamation getByUserSalle(Long user, Long salle) {
		Reclamation reclamation = null;
		for (Reclamation rec : ReclamationDB.reclamations) {
			if (rec.getSalle().getId() == salle && rec.getUser().getId() == user) {
				reclamation = rec;

			}
		}
		LOGGER.info( reclamation);
		return reclamation;
	}
}
