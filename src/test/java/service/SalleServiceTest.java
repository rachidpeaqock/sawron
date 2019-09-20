package service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.service.DepartementService;
import com.tenor.tsf.gs.service.ReservationService;
import com.tenor.tsf.gs.service.SalleService;
import com.tenor.tsf.gs.service.UtilisateurService;

public class SalleServiceTest {
	private static Logger LOGGER = Logger.getLogger(ReservationServiceTest.class);
	
	
	@Test
	public void testCreateSalle(){
		Salle salle=new Salle();
		salle.setLibelle("Salle A");
		salle=SalleService.createSalle(salle);
		assertEquals("Salle A",salle.getLibelle());
		
	}
	@Test
	public void testUpdateSalle(){
		Salle salle=new Salle();
		salle.setLibelle("Salle A");
		salle=SalleService.createSalle(salle);
		salle.setLibelle("Salle B");
		SalleService.updateSalle(salle);
		salle=SalleService.findSalleById(salle.getId());
		assertEquals("Salle B",salle.getLibelle());
		
	}
	@Test
	public void testGetSalleDispo(){
		Salle salleA=new Salle();
		salleA.setLibelle("Salle A");
		salleA=SalleService.createSalle(salleA);
		Salle salleB=new Salle();
		salleB.setLibelle("Salle B");
		salleB=SalleService.createSalle(salleB);
		Salle salleC=new Salle();
		salleC.setLibelle("Salle C");
		salleC=SalleService.createSalle(salleC);
		Utilisateur user=new Utilisateur();
		user.setId(1l);
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		Departement departement=new Departement();
		departement.setLibelle("DSI");
	//	departement=DepartementService.createDepartement(departement);
		user.setDepartement(departement);
		user=UtilisateurService.createUser(user);
		LocalDate dateDebut = LocalDate.of(2019, 9, 18);
		LocalDate dateFin = LocalDate.of(2019, 9, 20);
		Reservation reservation =new Reservation();
		reservation.setSalle(salleA);
		reservation.setUser(user);
		reservation.setDateDebut(dateDebut);
		reservation.setDateFin(dateFin);
		ReservationService.reserve(reservation);
		LOGGER.info("list salle dispo " + SalleService.getSalleDispo(dateDebut, dateFin));

		
	}
	
	

}
