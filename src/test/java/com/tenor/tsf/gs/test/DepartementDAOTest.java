package com.tenor.tsf.gs.test;

import static org.junit.Assert.assertEquals;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tenor.tsf.gs.dao.DepartementDao;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DepartementDao.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)

public class DepartementDAOTest {
	
	@Autowired
	DepartementDao departementDao ;
	public static final Logger LOGGER = Logger.getLogger(DepartementDao.class);
	

	@Test
	public void create() throws AllreadyExistException, EmptyFieldException {
		Departement departement =new Departement();
		Departement newDept = new Departement();
		departement.setLibelle("DSI");
		newDept.setLibelle("DS");
		LOGGER.info(departement);

		departement = departementDao.create(departement);
		newDept = departementDao.create(newDept);
		LOGGER.info(departement);
		LOGGER.info(newDept);
		
		// assertNull(departementDao.getById(dept.getId()).getLibelle());
		assertEquals("DSI", departement.getLibelle());
		

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() throws AllreadyExistException, EmptyFieldException {
		Departement dept = new Departement();
		dept.setLibelle("");
		dept = departementDao.create(dept);
		// assertNull(departementDao.getById(dept.getId()).getLibelle());
		assertEquals("DSI", dept.getLibelle());
		

	}
	@Test(expected = AllreadyExistException.class)
	public void allreadyExistException() throws AllreadyExistException, EmptyFieldException {
		Departement dept = new Departement();
		dept.setLibelle("DJJ");
		Departement departement=new Departement();
		departement.setLibelle("DJJ");
		departementDao.create(dept);
		departementDao.create(departement);
		dept = departementDao.create(dept);
		// assertNull(departementDao.getById(dept.getId()).getLibelle());
		assertEquals("DSI", dept.getLibelle());
		
		
	}

	@Test
	public void update() throws NotFoundException, AllreadyExistException, EmptyFieldException {
		Departement dept = new Departement();
		dept.setLibelle("RH");
		dept=departementDao.create(dept);
		dept.setLibelle("DSI");
		departementDao.update(dept);
		String libelle=departementDao.getById(dept.getId()).getLibelle();
		assertEquals("DSI", libelle);

	}
	//return null if object not found
	@Test(expected = NullPointerException.class)
	public void nullGivenToUpdate() throws NotFoundException {
		Departement dept = null;
		departementDao.update(dept);

	}
	//return null if object not found
	@Test(expected = NotFoundException.class)
	public void notFoudToUpdate() throws NotFoundException {
		Departement newDepartement = new Departement();
		newDepartement.setLibelle("MMM");		
		departementDao.update(newDepartement);
		
	}

	@Test
	public void getAll() {
		LOGGER.info(departementDao.getAll());
		LOGGER.info(departementDao.getAll().size());
		departementDao.getAll().clear();
		assertEquals(0, departementDao.getAll().size());
	}

	@Test
	public void delete() throws NotFoundException, AllreadyExistException, EmptyFieldException {
		Departement dept = new Departement();
		dept.setLibelle("RHH");
		dept=departementDao.create(dept);
		Departement departement = new Departement();
		departement.setLibelle("DSIID");
		departement=departementDao.create(departement);
		departementDao.delete(departement);
		departement = departementDao.getByName("DSIID");
		assertEquals(null, departement);

	}
	//return null if object not found
	@Test(expected = NullPointerException.class)
	public void nullGivenToDelete() throws NotFoundException  {
		Departement dept = null;
		departementDao.delete(dept);

	}
	@Test(expected = NotFoundException.class)
	public void notFoundToDelete() throws NotFoundException  {
		Departement newDepartement = new Departement();
		newDepartement.setLibelle("MMM");
		departementDao.delete(newDepartement);
		
	}
	@Test
	public void getById() throws AllreadyExistException, EmptyFieldException
	{
		Departement dept = new Departement();
		dept.setLibelle("1RH");
		departementDao.create(dept);
		dept=departementDao.getById(dept.getId());
		assertEquals("1RH", dept.getLibelle());

		
	}
	
	@Test
	public void getByIdNotFound() throws AllreadyExistException, EmptyFieldException
	{
		Departement dept = new Departement();
		dept.setLibelle("RH");
		dept=departementDao.create(dept);
		 dept=departementDao.getById(100L);
		assertEquals(null,dept);
		
		
	}
	@Test
	public void getByName() 
	{
		Departement dept = new Departement();
		dept.setLibelle("DHJK");
		dept=departementDao.create(dept);
		dept=departementDao.getByName("DHJK");
		assertEquals("DHJK",dept.getLibelle());		
		
	}
	@Test
	public void nullGetByName() 
	{
		Departement dept = new Departement();
		dept.setLibelle("DHJKkk");
		//dept=departementDao.create(dept);
		dept=departementDao.getByName("DHJKkk");
		assertEquals(null,dept);		
		
	}

}
