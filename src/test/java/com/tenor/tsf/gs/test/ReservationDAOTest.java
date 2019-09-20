package com.tenor.tsf.gs.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.tenor.tsf.gs.dao.ReservationDao;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.AllreadyReservedException;
import com.tenor.tsf.gs.exceptions.DateExpection;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReservationDAOTest {

	ReservationDao resDAO = new ReservationDao();

	@Test
	public void getAll() {
		List<Reservation> list = resDAO.getAll();
		Boolean output = list != null ? true : false;
		assertEquals(true, output);
	}

	@Test
	public void create() {
		Reservation res = new Reservation();
		Reservation newRes = new Reservation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setId(1l);
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setId(2l);
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1l);
		salle.setLibelle("DSI");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 16);
		LocalDate dateFin = LocalDate.of(2020, 9, 20);
		LocalDate ndateDebut = LocalDate.of(2020, 9, 20);
		LocalDate ndateFin = LocalDate.of(2020, 9, 22);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		newRes.setUser(newUser);
		newRes.setSalle(salle);
		newRes.setDateDebut(ndateDebut);
		newRes.setDateFin(ndateFin);
		res = resDAO.create(res);
		newRes = resDAO.create(newRes);
		assertEquals("khalid", newRes.getUser().getFirstName());

	}

	@Test(expected = EmptyFieldException.class)
	public void nonCreate() throws EmptyFieldException, DateExpection {
		Reservation res = new Reservation();
		Reservation newRes = new Reservation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setId(3l);
		newUser.setId(4l);
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = null;
		LocalDate dateFin = LocalDate.of(2020, 9, 13);
		LocalDate ndateDebut = LocalDate.of(2020, 8, 10);
		LocalDate ndateFin = LocalDate.of(2020, 9, 8);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		newRes.setUser(newUser);
		newRes.setSalle(salle);
		newRes.setDateDebut(dateDebut);
		newRes.setDateDebut(ndateDebut);
		newRes.setDateFin(ndateFin);
		res = resDAO.create(res);
		newRes = resDAO.create(newRes);
		assertEquals("rachid", res.getUser().getFirstName());

	}

	@Test(expected = DateExpection.class)
	public void DateExpCreate() throws EmptyFieldException, DateExpection {
		Reservation res = new Reservation();
		Reservation newRes = new Reservation();
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setId(5l);
		newUser.setId(6l);
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 15);
		LocalDate dateFin = LocalDate.of(2020, 9, 13);
		LocalDate ndateDebut = LocalDate.of(2020, 9, 13);
		LocalDate ndateFin = LocalDate.of(2020, 9, 13);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		newRes.setUser(newUser);
		newRes.setSalle(salle);
		newRes.setDateDebut(dateDebut);
		newRes.setDateDebut(ndateDebut);
		newRes.setDateFin(ndateFin);
		res = resDAO.create(res);
		newRes = resDAO.create(newRes);

	}

	@Test(expected = AllreadyReservedException.class)
	public void AllReadyReserved() throws EmptyFieldException, DateExpection {
		Reservation res = new Reservation();
		Reservation newRes = new Reservation();
		
		Utilisateur user = new Utilisateur();
		Utilisateur newUser = new Utilisateur();
		user.setId(7l);
		newUser.setId(8l);
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		newUser.setFirstName("khalid");
		newUser.setSecondName("ouahmane");
		
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		
		res.setUser(user);
		res.setSalle(salle);
		
		LocalDate dateDebut = LocalDate.of(2020, 9, 17);
		LocalDate dateFin = LocalDate.of(2020, 9, 19);
		LocalDate ndateDebut = LocalDate.of(2020, 9, 18);
		LocalDate ndateFin = LocalDate.of(2020, 9, 22);
		
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		
		newRes.setUser(newUser);
		newRes.setSalle(salle);
		
		newRes.setDateDebut(ndateDebut);
		newRes.setDateFin(ndateFin);
		
		res = resDAO.create(res);
		newRes = resDAO.create(newRes);

	}

	@Test
	public void update() throws NotFoundException {
		Reservation res = new Reservation();
		Utilisateur user = new Utilisateur();
		user.setId(9l);
		user.setFirstName("mohemed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 12, 16);
		LocalDate dateFin = LocalDate.of(2020, 12, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res = resDAO.create(res);
		salle.setLibelle("RH");
		res.setSalle(salle);
		resDAO.update(res);
		res = resDAO.getByUserAndSalle(user.getId(), salle.getId());
		assertEquals("RH", res.getSalle().getLibelle());
	

	}
	@Test(expected = NotFoundException.class)
	public void notFoundExcpUpdate() throws NotFoundException {
		Reservation res = new Reservation();
		Utilisateur user = new Utilisateur();
		user.setId(10l);
		user.setFirstName("mohemed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 16);
		LocalDate dateFin = LocalDate.of(2020, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		//res = resDAO.create(res);
		salle.setLibelle("RH");
		res.setSalle(salle);
		resDAO.update(res);
		res = resDAO.getByUserAndSalle(user.getId(), salle.getId());
		assertEquals("RH", res.getSalle().getLibelle());
		
		
	}

	@Test
	public void delete() throws NotFoundException {
		Reservation res = new Reservation();
		Utilisateur user = new Utilisateur();
		user.setId(11l);
		user.setFirstName("jamal");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 12, 16);
		LocalDate dateFin = LocalDate.of(2020, 12, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res = resDAO.create(res);
		resDAO.delete(res);
		res = resDAO.getByUserAndSalle(user.getId(), salle.getId());
		assertEquals(null, res);
		

	}
	@Test(expected = NotFoundException.class)
	public void NotFoundExcpdelete() throws NotFoundException {
		Reservation res = new Reservation();
		Utilisateur user = new Utilisateur();
		user.setId(12l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 16);
		LocalDate dateFin = LocalDate.of(2020, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		//res = resDAO.create(res);
		resDAO.delete(res);
		
		
		
	}
	@Test
	public void getById()  {
		Reservation res = new Reservation();
		Utilisateur user = new Utilisateur();
		user.setId(13l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2021, 9, 16);
		LocalDate dateFin = LocalDate.of(2021, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res = resDAO.create(res);
		res=resDAO.getById(res.getId());
		assertEquals("amed", res.getUser().getFirstName());		
		
	}
	@Test
	public void nullgetById()  {
		Reservation res = new Reservation();
		res.setId(100L);
		Utilisateur user = new Utilisateur();
		user.setId(15l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 16);
		LocalDate dateFin = LocalDate.of(2020, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		//res = resDAO.create(res);
		res=resDAO.getById(res.getId());
		assertEquals(null, res);		
		
	}
	@Test
	public void getBySalleUser()  {
		Reservation res = new Reservation();
		res.setId(100L);
		Utilisateur user = new Utilisateur();
		user.setId(14l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(1L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 16);
		LocalDate dateFin = LocalDate.of(2020, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		//res = resDAO.create(res);
		res=resDAO.getById(res.getId());
		assertEquals(null, res);		
		
	}
	@Test
	public void getBySalleDate()  {
		Reservation res = new Reservation();
		res.setId(101L);
		Utilisateur user = new Utilisateur();
		user.setId(14l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(10L);
		salle.setLibelle("Salle 3");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2021, 9, 16);
		LocalDate dateFin = LocalDate.of(2021, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res = resDAO.create(res);
		res=resDAO.getResBySalleAndDate(salle, dateDebut, dateFin);
		assertEquals(dateDebut, res.getDateDebut());		
		
	}
	@Test
	public void notFoundBySalleDate()  {
		Reservation res = new Reservation();
		res.setId(102L);
		Utilisateur user = new Utilisateur();
		user.setId(14l);
		user.setFirstName("amed");
		user.setSecondName("ouahmane");
		Salle salle = new Salle();
		salle.setId(12L);
		salle.setLibelle("Salle 4");
		res.setUser(user);
		res.setSalle(salle);
		LocalDate dateDebut = LocalDate.of(2021, 9, 16);
		LocalDate dateFin = LocalDate.of(2021, 9, 19);
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		//res = resDAO.create(res);
		res=resDAO.getResBySalleAndDate(salle, dateDebut, dateFin);
		assertEquals(null, res);		
		
	}

}
