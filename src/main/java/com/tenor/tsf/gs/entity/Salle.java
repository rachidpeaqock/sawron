package com.tenor.tsf.gs.entity;

import java.util.List;

import lombok.Data;
@Data
public class Salle {

	private Long id;
	private String libelle;
	private List<Materiel> materiels;
	private Integer capacite;

	
	
	
	
	
}
