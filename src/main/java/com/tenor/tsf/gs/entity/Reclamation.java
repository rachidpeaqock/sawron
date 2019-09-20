package com.tenor.tsf.gs.entity;

import java.time.LocalDate;


import lombok.Data;
@Data
public class Reclamation {

	
	private Long id ;
	private LocalDate dateRec;
	private Utilisateur user;
	private Salle salle;
	private Integer Statu;
	private String message;
	
	
	
	
}
