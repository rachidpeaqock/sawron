package com.tenor.tsf.gs.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.tenor.tsf.gs.dao.UtilisateurDao;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class UtilisateurDaoTest {

	UtilisateurDao UtilisateurDao = new UtilisateurDao();
	public static final Logger logger = Logger.getLogger(UtilisateurDaoTest.class);

	@Test
	public void getAll() {
		List<Utilisateur> list = UtilisateurDao.getAll();
		Boolean output = list != null ? true : false;
		assertEquals(true, output);
	}

	@Test
	public void create() throws EmptyFieldException {
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouhamane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		newUser.setDepartement(departement);
		user = UtilisateurDao.create(user);
		newUser = UtilisateurDao.create(newUser);

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() throws EmptyFieldException {
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("");
		user.setSecondName("ouahmane");
		newUser.setFirstName("");
		Departement departement = new Departement();
		departement.setLibelle("");
		user.setDepartement(departement);
		newUser.setDepartement(departement);
		user = UtilisateurDao.create(user);
		newUser = UtilisateurDao.create(newUser);

	}

	@Test(expected = AllreadyExistException.class)
	public void allreadyExistCreate() {
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("rachid ");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("khalid");
		Departement departement = new Departement();
		departement.setLibelle("");
		user.setDepartement(departement);
		newUser.setDepartement(departement);
		user = UtilisateurDao.create(user);
		newUser.setId(user.getId());
		newUser = UtilisateurDao.create(newUser);

	}

	@Test
	public void update() {
		Utilisateur user = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		user = UtilisateurDao.create(user);
		user.setFirstName("Ahmed");
		UtilisateurDao.update(user);
		user = UtilisateurDao.getByName("Ahmed");
		assertEquals("Ahmed", user.getFirstName());

	}

	@Test(expected = NotFoundException.class)
	public void nonUpdate() throws NotFoundException {
		Utilisateur user = new Utilisateur();
		user.setFirstName("mohamed");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		// user = UtilisateurDao.create(user);
		user.setFirstName("Ahmed");
		UtilisateurDao.update(user);
		user = UtilisateurDao.getByName("Ahmed");
		assertEquals("Ahmed", user.getFirstName());

	}

	@Test()
	public void delete() throws NotFoundException {
		Utilisateur user = new Utilisateur();
		user.setFirstName("safi");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		user = UtilisateurDao.create(user);
		UtilisateurDao.delete(user);
		user = UtilisateurDao.getByName("safi");
		assertEquals(null, user);

	}

	@Test(expected = NotFoundException.class)
	public void notFondToDelete() throws NotFoundException {
		Utilisateur user = new Utilisateur();
		user.setFirstName("safi");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		//user = UtilisateurDao.create(user);
		UtilisateurDao.delete(user);
		user = UtilisateurDao.getByName("safi");
		assertEquals(null, user);

	}
	@Test
	public void getById() throws NotFoundException {
		Utilisateur user = new Utilisateur();
		user.setFirstName("safi");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		user = UtilisateurDao.create(user);
		user = UtilisateurDao.getById(user.getId());
		assertEquals("safi", user.getFirstName());
		
	}
	@Test
	public void getByName()  {
		Utilisateur user = new Utilisateur();
		user.setFirstName("safi");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		user = UtilisateurDao.create(user);
		user = UtilisateurDao.getByName("safi");
		assertEquals("safi", user.getFirstName());
		
	}
	@Test
	public void getByIdNotFound() throws NotFoundException {
		Utilisateur user = new Utilisateur();
		user.setFirstName("kamal");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
	//	user = UtilisateurDao.create(user);
		user = UtilisateurDao.getById(user.getId());
		assertEquals(null, user);
		
	}
	@Test
	public void getByNameNotfound()  {
		Utilisateur user = new Utilisateur();
		user.setFirstName("kamal");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("dsi");
		user.setDepartement(departement);
		//user = UtilisateurDao.create(user);
		user = UtilisateurDao.getByName("safi");
		assertEquals(null, user);
		
	}
}
