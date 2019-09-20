package com.tenor.tsf.gs.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.dao.DepartementDao;
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.Utilisateur;
import com.tenor.tsf.gs.repository.DepartementRepository;

@Service
public class DepartementService {
	
//	@Autowired
//	private static DepartementDao departementDao ;
//	
//	public static Departement createDepartement(Departement departement) {
//		Departement depart = departementDao.create(departement);
//		return depart;
//	}
//
//	public static void deleteDepartement(Departement departement) {
//		departementDao.delete(departement);
//	
//	}
//
//	public static void updateDepartement(Departement departement) {
//		departementDao.create(departement);
//
//	}
//	public static void addUser(Departement departement){
//		Utilisateur user=UtilisateurService.getUserBydepartementId(departement.getId());
//		departement.getUsers().add(user);
//	}
	
	@Autowired
	private DepartementRepository<Departement> departementRepository;
	

//	@Autowired
//	public DepartementService(DepartementRepository<Departement> departementRepository) {
//		this.departementRepository =departementRepository;
//		
//	}



	@Transactional
	public List<Departement> getAllDepartements() {
		return (List<Departement>) departementRepository.findAll();
	}

	

	@Transactional
	public Optional<Departement> getById(Long id) {
		return departementRepository.findById(id);
	}

	@Transactional
	public void deleteDepartement(Departement DepartementId) {
		departementRepository.delete(DepartementId);
	}

	@Transactional
	public boolean addDepartement(Departement Departement) {
		return departementRepository.save(Departement) != null;
	}

	@Transactional
	public boolean updateDepartement(Departement Departement) {
		return departementRepository.save(Departement) != null;
	}


	
	
}
