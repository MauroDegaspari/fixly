package com.helpdesk.fixly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.helpdesk.fixly.services.CargaBaseTstService;

@Configuration
@Profile("tst")
public class CargaBaseTstConfig {
	
	@Autowired
	private CargaBaseTstService carga;
	
	
	public void ChamaCargaTst() {
		this.carga.CargaBaseTst();
	}
		
}
