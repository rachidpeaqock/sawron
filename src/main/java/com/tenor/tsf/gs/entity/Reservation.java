package com.tenor.tsf.gs.entity;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Reservation {
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Utilisateur user;
	private Salle salle;

	
}
