package service;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.tenor.tsf.gs.dao.DepartementDao;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.AllreadyReservedException;
import com.tenor.tsf.gs.service.DepartementService;
import com.tenor.tsf.gs.service.ReservationService;
import com.tenor.tsf.gs.service.SalleService;
import com.tenor.tsf.gs.service.UtilisateurService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DepartementDao.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ReservationServiceTest {

	private static Logger LOGGER = Logger.getLogger(ReservationServiceTest.class);
	@Autowired 
	DepartementService departService;
	
	@Test
	public void testReserve() {
		Utilisateur user = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("DSI");
		//departement = DepartementService.createDepartement(departement);
		user.setDepartement(departement);
		user = UtilisateurService.createUser(user);
		Salle salle = new Salle();
		salle.setLibelle("DSI");
		salle = SalleService.createSalle(salle);
		LocalDate dateDebut = LocalDate.of(2020, 9, 17);
		LocalDate dateFin = LocalDate.of(2020, 9, 20);
		Reservation reservation = new Reservation();
		reservation.setSalle(salle);
		reservation.setUser(user);
		reservation.setDateDebut(dateDebut);
		reservation.setDateFin(dateFin);
		ReservationService.reserve(reservation);

	}

	@Test(expected = AllreadyReservedException.class)
	public void checkAlreadyReserved() {
		Utilisateur user = new Utilisateur();
		user.setFirstName("rachid");
		user.setSecondName("ouahmane");
		Departement departement = new Departement();
		departement.setLibelle("DSII");
		//departement = DepartementService.createDepartement(departement);
		user.setDepartement(departement);
		user = UtilisateurService.createUser(user);

		Salle salle = new Salle();
		salle.setLibelle("DSIII");
		salle = SalleService.createSalle(salle);

		LocalDate dateDebut = LocalDate.of(2020, 9, 17);
		LocalDate dateFin = LocalDate.of(2020, 9, 20);
		Reservation reservation = new Reservation();
		reservation.setSalle(salle);
		reservation.setUser(user);
		reservation.setDateDebut(dateDebut);
		reservation.setDateFin(dateFin);
		ReservationService.reserve(reservation);

		LocalDate ndateDebut = LocalDate.of(2019, 9, 18);
		LocalDate ndateFin = LocalDate.of(2019, 9, 20);
		Reservation nreservation = new Reservation();
		nreservation.setSalle(salle);
		nreservation.setUser(user);
		nreservation.setDateDebut(ndateDebut);
		nreservation.setDateFin(ndateFin);
		ReservationService.reserve(nreservation);
	}

}
