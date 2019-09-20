package com.tenor.tsf.gs.dao;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.tenor.tsf.gs.db.UtilisateurDB;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class UtilisateurDao implements AbstractDAO<Utilisateur> {
	private static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	private static Long count=0l;
	public Utilisateur create(Utilisateur user) {

		Validate.notNull(user, "object given is null");
		Utilisateur mUser = getById(user.getId());
		if (mUser != null)
			throw new AllreadyExistException("user "+user.getId()+" allready exsit");

		if (user.getFirstName() == "")
			throw new EmptyFieldException("user first name is empty");
		if (user.getSecondName() == "")
			throw new EmptyFieldException("user secound name is empty");
		if (user.getDepartement() == null)
			throw new EmptyFieldException("user departement name is empty");
		
		LOGGER.info( mUser);

		count += 1L;
		user.setId(count);
		UtilisateurDB.utilisateurs.add(user);
		mUser= this.getById(user.getId());
		
		LOGGER.info( mUser);

		return mUser;

	}

	public void update(Utilisateur user) {
		Validate.notNull(user, "object given is null");
		Utilisateur mUser = getById(user.getId());
		if (mUser == null)
			throw new NotFoundException("user "+user.getId()+"not found");
		LOGGER.info( mUser);
		int index = UtilisateurDB.utilisateurs.indexOf(mUser);
		UtilisateurDB.utilisateurs.set(index, user);
		mUser = getById(user.getId());
		LOGGER.info( mUser);

	}

	public void delete(Utilisateur user) {
		Validate.notNull(user, "object given is null");
		Utilisateur mUser = this.getById(user.getId());
		if (mUser == null)
			throw new NotFoundException("user "+user.getId()+"not found");
		LOGGER.info( mUser);
		UtilisateurDB.utilisateurs.remove(mUser);
	}

	public List<Utilisateur> getAll() {
		return UtilisateurDB.utilisateurs;
	}

	public Utilisateur getById(Long id) {
		Utilisateur utilisateur = null;
		for (Utilisateur user : UtilisateurDB.utilisateurs) {
			if (user.getId() == id) {
				utilisateur = user;

			}
		}
		LOGGER.info( utilisateur);
		return utilisateur;
	}

	public Utilisateur getByName(String name) {
		Utilisateur utilisateur = null;
		for (Utilisateur user : UtilisateurDB.utilisateurs) {
			if (user.getFirstName() == name) {
				utilisateur = user;

			}
		}
		LOGGER.info( utilisateur);
		return utilisateur;
	}
	public Utilisateur getByDepartementId(Long id) {
		Utilisateur utilisateur = null;
		for (Utilisateur user : UtilisateurDB.utilisateurs) {
			if (user.getDepartement().getId() == id) {
				utilisateur = user;
				
			}
		}
		LOGGER.info( utilisateur);
		return utilisateur;
	}
}