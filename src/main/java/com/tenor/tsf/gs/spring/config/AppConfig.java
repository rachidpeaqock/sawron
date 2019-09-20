package com.tenor.tsf.gs.spring.config;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.repository.DepartementRepository;
import com.tenor.tsf.gs.service.DepartementService;

@Configuration
public class AppConfig {

	private DepartementRepository<Departement> departementRepository;

	public AppConfig(DepartementRepository<Departement> departementRepository) {

		this.departementRepository = departementRepository;
	}

	@Bean
	public DepartementService DepServ() {

		return new DepartementService();
	}

}
