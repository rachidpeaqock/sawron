package com.tenor.tsf.gs.service;

import static org.junit.Assert.*;

import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.tenor.tsf.gs.dao.DepartementDao;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.spring.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DepartementServiceTest {

	@Autowired
	DepartementService departementService;
	
	@Test 
	public void SaveDepartementTest() {
		Departement departement=new Departement();
		departement.setLibelle("DSI");
		Boolean addedBoolean=false;
		addedBoolean=departementService.addDepartement(departement);
		assertEquals(true, addedBoolean);
		
		
	
	}
	

}
