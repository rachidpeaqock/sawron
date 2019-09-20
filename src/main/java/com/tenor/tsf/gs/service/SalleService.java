package com.tenor.tsf.gs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tenor.tsf.gs.dao.MaterielDao;
import com.tenor.tsf.gs.dao.ReservationDao;
import com.tenor.tsf.gs.dao.SalleDao;
import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;

public class SalleService {
	@Autowired
	private static SalleDao salleDao;
	private static ReservationDao reservationDao = new ReservationDao();
	private static MaterielDao materielDao=new MaterielDao();
	public static Salle createSalle(Salle salle) {
		return salleDao.create(salle);
		
	}

	public static void updateSalle(Salle salle) {
		salleDao.update(salle);
	}
	public static void AddMateriel(Salle salle)
	{
		Salle sal=salleDao.getById(salle.getId());
		Materiel materiel=materielDao.findBySalleId(salle.getId());
		sal.getMateriels().add(materiel);
		
	}
	public static void deleteSalle(Salle salle) {
		salleDao.delete(salle);
	}

	public static List<Salle> getAllSalle() {
		return salleDao.getAll();
	}
	public static Salle findSalleById(Long sal) {
		Salle salle=salleDao.getById(sal);
		return salle;
	}
	public static List<Salle> getSalleDispo(LocalDate dateDentrer, LocalDate dateFin) {
		List<Salle> salles = new ArrayList<Salle>();
		Reservation res = null;
		for (Salle salle : salleDao.getAll()) {
			res = reservationDao.getResBySalleAndDate(salle, dateDentrer, dateFin);
			if (res == null)
				salles.add(salle);
		}
		return salles;
	}

}
