package com.tenor.tsf.gs.test;

import org.junit.Test;

import com.tenor.tsf.gs.dao.MaterielDao;
import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;

public class MaterielDAOTest {

	MaterielDao matDAO = new MaterielDao();

	@Test
	public void create() throws EmptyFieldException {
		Materiel mat = new Materiel();
		Materiel newMat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP");
		newMat.setLibelle("ASSUS");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		newMat.setSalle(salle);
		mat = matDAO.create(mat);
		newMat = matDAO.create(newMat);
		assertEquals("HP", mat.getLibelle());

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat = matDAO.create(mat);
		assertEquals("HP", mat.getLibelle());

	}

	@Test(expected = AllreadyExistException.class)
	public void allreadyExistCreate() {
		Materiel mat = new Materiel();
		Materiel newMat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("DELL");
		newMat.setLibelle("DELL");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		newMat.setSalle(salle);
		mat = matDAO.create(mat);
		newMat.setId(mat.getId());
		newMat = matDAO.create(newMat);
	}

	@Test
	public void update() throws NotFoundException {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP650");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat = matDAO.create(mat);
		mat.setLibelle("DELL90");
		matDAO.update(mat);
		assertEquals("DELL90", matDAO.findById(mat.getId()).getLibelle());

	}

	@Test(expected = NotFoundException.class)
	public void nonUpdate() throws NotFoundException {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP650");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		// mat = matDAO.create(mat);
		// mat.setLibelle("DELL90");
		matDAO.update(mat);
		assertEquals("DELL90", matDAO.findById(mat.getId()).getLibelle());

	}

	public void getAll() {

		List<Materiel> list = matDAO.getAll();
		assertEquals(1, list.size());
	}

	@Test
	public void delete() throws NotFoundException {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP6501");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat = matDAO.create(mat);
		matDAO.delete(mat);
		mat = matDAO.getByName("HP6501");
		assertNull(mat);

	}

	@Test(expected = NotFoundException.class)
	public void nonDelete() throws NotFoundException {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP65011");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		//mat = matDAO.create(mat);
		matDAO.delete(mat);
		mat = matDAO.getByName("ASSUS");
		assertNull(mat);

	}

	@Test
	public void getByName() {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP6501111");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat = matDAO.create(mat);
		mat=matDAO.getByName("HP6501111");
		assertEquals("HP6501111", mat.getLibelle());

	}
	@Test
	public void nullGetByName() {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("HP650999");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		//mat = matDAO.create(mat);
		mat=matDAO.getByName("HP650999");
		assertEquals(null, mat);

	}
	@Test
	public void getById() {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("DELL11");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat = matDAO.create(mat);
		mat=matDAO.findById(mat.getId());
		assertEquals("DELL11", mat.getLibelle());
		
	}
	@Test
	public void nullGetByID() {
		Materiel mat = new Materiel();
		Salle salle = new Salle();
		mat.setLibelle("DELL11");
		salle.setLibelle("DSI");
		mat.setSalle(salle);
		mat.setId(100l);
		//mat = matDAO.create(mat);
		mat=matDAO.findById(mat.getId());
		assertEquals(null, mat);
		
		
	}

}
