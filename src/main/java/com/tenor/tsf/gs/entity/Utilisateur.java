package com.tenor.tsf.gs.entity;

import lombok.Data;
@Data
public class Utilisateur {

	private Long id;
	private String firstName;
	private String secondName;
	private String function;
	private String pseudo;
	private String password;
	private Departement departement;
	
	
	
}
