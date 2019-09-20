package com.tenor.tsf.gs.service;

import java.time.LocalDate;

import com.tenor.tsf.gs.dao.ReclamationDao;
import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.exceptions.DateExpection;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReclamationService {
	private static ReclamationDao reclamationDao = new ReclamationDao();

	public static Reclamation reclame(Reclamation reclamation) {
		Salle salle = SalleService.findSalleById(reclamation.getSalle().getId());
		Utilisateur user = UtilisateurService.getUserById(reclamation.getId());

		if (salle == null)
			throw new NotFoundException("salle given dosn't existe in data base");
		if (user == null)
			throw new NotFoundException("user given dosn't eciste in data base ");
		if (reclamation.getDateRec().isBefore(LocalDate.now()))
			throw new DateExpection("Date reclamation should not be before current date");

		Reclamation rec = reclamationDao.create(reclamation);
		return rec;
	}

	public static void updateReclamattion(Reclamation reclamation) {

		reclamationDao.update(reclamation);
	}

	public static void annuleReclamation(Reclamation reclamation) {

		reclamationDao.delete(reclamation);
	}

}
