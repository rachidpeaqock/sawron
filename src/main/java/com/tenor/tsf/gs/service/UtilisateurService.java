package com.tenor.tsf.gs.service;

import com.tenor.tsf.gs.dao.UtilisateurDao;
import com.tenor.tsf.gs.entity.Utilisateur;

public class UtilisateurService {

	private static UtilisateurDao utilisateurDao = new UtilisateurDao();

	// private static Logger logger =Logger.getLogger(UtilisateurService.class);
	public static Utilisateur createUser(Utilisateur user) {

		Utilisateur utilisateur = utilisateurDao.create(user);
		return utilisateur;
	}

	public static void updateUser(Utilisateur user) {
		utilisateurDao.update(user);
	}

	public static void deleteUser(Utilisateur user) {
		utilisateurDao.delete(user);
	}

	public static Utilisateur getUserById(Long id) {
		return utilisateurDao.getById(id);
	}

	public static Utilisateur getUserBydepartementId(Long id) {
		return utilisateurDao.getByDepartementId(id);
	}

}
