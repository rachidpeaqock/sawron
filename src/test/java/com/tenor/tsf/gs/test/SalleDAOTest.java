package com.tenor.tsf.gs.test;

import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tenor.tsf.gs.dao.SalleDao;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalleDAOTest {

	SalleDao SalleDao = new SalleDao();

	@Test
	public void getAll() {
		List<Salle> list = SalleDao.getAll();
		Boolean output = list != null ? true : false;
		assertEquals(true, output);
	}

	@Test
	public void create() throws EmptyFieldException {
		Salle salle = new Salle();
		Salle newSalle = new Salle();
		salle.setLibelle("DSI");
		newSalle.setLibelle("RH");
		salle = SalleDao.create(salle);
		newSalle = SalleDao.create(newSalle);
		assertEquals("DSI", salle.getLibelle());

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() throws EmptyFieldException {
		Salle salle = new Salle();
		Salle newSalle = new Salle();
		salle.setLibelle("");
		newSalle.setLibelle("");
		salle = SalleDao.create(salle);
		// newSalle.setId(salle.getId());
		newSalle = SalleDao.create(newSalle);

	}

	@Test(expected = AllreadyExistException.class)
	public void allreadyExistCreate() throws EmptyFieldException {
		Salle salle = new Salle();
		Salle newSalle = new Salle();
		salle.setLibelle("salle1");
		newSalle.setLibelle("salle2");
		salle = SalleDao.create(salle);
		newSalle.setId(salle.getId());
		newSalle = SalleDao.create(newSalle);

	}

	@Test
	public void update()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle6");
		salle = SalleDao.create(salle);
		// newSalle.setId(salle.getId());
		salle.setLibelle("salle10");
		SalleDao.update(salle);
		salle = SalleDao.getByName("salle10");
		assertEquals("salle10", salle.getLibelle());

	}

	@Test(expected = NotFoundException.class)
	public void nonUpdate()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle7");
		//salle = SalleDao.create(salle);
		// newSalle.setId(salle.getId());
		// salle.setLibelle("salle5");
		SalleDao.update(salle);
		salle = SalleDao.getByName("salle5");
		assertEquals("salle5", salle.getLibelle());

	}

	@Test
	public void delete()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle7");
		salle = SalleDao.create(salle);
		SalleDao.delete(salle);
		salle = SalleDao.getByName("Salle7");
		assertEquals(null, salle);

	}

	@Test(expected = NotFoundException.class)
	public void nonDelete()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle8");
		//salle = SalleDao.create(salle);
		SalleDao.delete(salle);
		salle = SalleDao.getByName("Salle8");
		assertEquals(null, salle);

	}
	@Test
	public void getById()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle21");
		salle = SalleDao.create(salle);
		salle = SalleDao.getById(salle.getId());
		assertEquals("Salle21", salle.getLibelle());
		
	}
	@Test
	public void notFoundGetById()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle21");
		//salle = SalleDao.create(salle);
		salle = SalleDao.getById(salle.getId());
		assertEquals(null, salle);
		
	}
	@Test
	public void getByName()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle999");
		salle = SalleDao.create(salle);
		salle = SalleDao.getByName("Salle999");
		assertEquals("Salle999", salle.getLibelle());
		
	}
	@Test
	public void getByNameNotFound()  {
		Salle salle = new Salle();
		salle.setLibelle("Salle9999");
		//salle = SalleDao.create(salle);
		salle = SalleDao.getByName("Salle9999");
		assertEquals(null, salle);
		
	}
}
