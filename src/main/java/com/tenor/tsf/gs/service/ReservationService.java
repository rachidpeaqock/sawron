package com.tenor.tsf.gs.service;

import com.tenor.tsf.gs.dao.ReservationDao;
import com.tenor.tsf.gs.dao.SalleDao;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReservationService {
	private static ReservationDao resDao = new ReservationDao();

	public static Reservation reserve(Reservation reservation) {
		Salle salle = SalleService.findSalleById(reservation.getSalle().getId());
		Utilisateur user = UtilisateurService.getUserById(reservation.getUser().getId());
		if (salle == null)
			throw new NotFoundException("Salle given dose not existe in database");
		if (user == null)
			throw new NotFoundException("user given dose not existe in database");

		return resDao.create(reservation);
	}
	public static void updateReservation(Reservation reservation) {
		Reservation resv=resDao.getById(reservation.getId());
		Salle salle = SalleService.findSalleById(reservation.getSalle().getId());
		Utilisateur user = UtilisateurService.getUserById(reservation.getUser().getId());
		if (resv==null) 
			throw new NotFoundException("Salle given dose not existe in database");
		if (salle == null)
			throw new NotFoundException("Salle given dose not existe in database");
		if (user == null)
			throw new NotFoundException("user given dose not existe in database");
		
		 resDao.update(reservation);
	}
	public static void annulerReserve(Reservation reservation) {
		Salle salle = SalleService.findSalleById(reservation.getSalle().getId());
		Utilisateur user = UtilisateurService.getUserById(reservation.getUser().getId());
		if (salle == null)
			throw new NotFoundException("Salle given dose not existe in database");
		if (user == null)
			throw new NotFoundException("user given dose not existe in database");
		
		 resDao.delete(reservation);
	}

}
