package com.tenor.tsf.gs.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.tenor.tsf.gs.dao.ReclamationDao;
import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReclamationDAOTest {

	ReclamationDao recDAO = new ReclamationDao();

	@Test
	public void getAll() {
		List<Reclamation> list = recDAO.getAll();
		Boolean output = list != null ? true : false;
		assertEquals(true, output);
	}

	@Test
	public void create() throws EmptyFieldException {
		Reclamation rec = new Reclamation();
		Reclamation newRec = new Reclamation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		newRec.setSalle(salle);
		newRec.setUser(newUser);
		rec = recDAO.create(rec);
		newRec = recDAO.create(newRec);
		Reclamation output = rec;
		assertEquals("Salle 1", output.getMessage());

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() throws EmptyFieldException {
		Reclamation rec = new Reclamation();
		Reclamation newRec = new Reclamation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("");
		newRec.setSalle(salle);
		newRec.setUser(newUser);
		rec = recDAO.create(rec);
		newRec = recDAO.create(newRec);

	}
	@Test(expected = AllreadyExistException.class)
	public void allreadyExistCreate() throws EmptyFieldException {
		Reclamation rec = new Reclamation();
		Reclamation newRec = new Reclamation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("DS");
		newRec.setSalle(salle);
		newRec.setUser(newUser);
		rec = recDAO.create(rec);
		newRec.setId(rec.getId());
		newRec = recDAO.create(newRec);
		
	}

	@Test
	public void update() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("Ahmed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		rec = recDAO.create(rec);
		rec.setMessage("Salle 0");
		recDAO.update(rec);
		Reclamation outReclamation = recDAO.getById(rec.getId());
		assertEquals("Salle 0", outReclamation.getMessage());

	}

	@Test(expected = NotFoundException.class)
	public void nonUpdate() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("Fath");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		// rec = recDAO.create(rec);
		rec.setMessage("Salle 0");
		recDAO.update(rec);
		Reclamation outReclamation = recDAO.getById(rec.getId());
		assertEquals("Salle 0", outReclamation.getMessage());

	}

	@Test
	public void delete() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("nasralah");
		user.setSecondName("ouahmane");
		user.setId(1l);
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		salle.setId(1L);
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		rec = recDAO.create(rec);
		recDAO.delete(rec);
		Reclamation output = recDAO.getByUserSalle(user.getId(),salle.getId());

		assertEquals(null, output);

	}

	@Test(expected = NotFoundException.class)
	public void nonDelete() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("abdlah");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		//rec = recDAO.create(rec);
		recDAO.delete(rec);

	}
	@Test()
	public void getById() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("hicham");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		rec = recDAO.create(rec);
		rec=recDAO.getById(rec.getId());
		assertEquals("Salle 1", rec.getMessage());
		
	}
	@Test()
	public void nullgetById() throws NotFoundException {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("hicham");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		//rec = recDAO.create(rec);
		rec=recDAO.getById(rec.getId());
		assertEquals(null, rec);
		
	}
	@Test()
	public void getByUserSalle()  {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("hicham");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		rec = recDAO.create(rec);
		rec=recDAO.getByUserSalle(user.getId(), salle.getId());
		assertEquals("Salle 1", rec.getMessage());
		
	}
	
	@Test()
	public void nullgetByUserSalle()  {
		Reclamation rec = new Reclamation();
		Utilisateur user = new Utilisateur();
		user.setFirstName("hicham");
		user.setSecondName("ouahmane");
		user.setId(101L);
		Salle salle = new Salle();
		salle.setId(101L);
		salle.setLibelle("DSI");
		rec.setUser(user);
		rec.setSalle(salle);
		rec.setMessage("Salle 1");
		//rec = recDAO.create(rec);
		rec=recDAO.getByUserSalle(user.getId(), salle.getId());
		assertEquals(null, rec);
		
	}

}
