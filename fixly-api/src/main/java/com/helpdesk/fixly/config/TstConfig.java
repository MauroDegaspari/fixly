package com.helpdesk.fixly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.helpdesk.fixly.services.CargaDadosBaseService;

@Configuration
@Profile("tst")
public class TstConfig {
	
	@Autowired
	private CargaDadosBaseService carga;
	
	@Bean
	public boolean ChamaCargaTst() {
		try {
			this.carga.CargaBaseDatos();
			
		} catch (Exception e) {
			
		}
		return false;
	}
		
}
