package com.helpdesk.fixly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.helpdesk.fixly.services.CargaDadosBaseService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private CargaDadosBaseService serviceCarga;
	
	@Value("${spring.jpa.hibenate.ddl-auto}")
	private String valor;
	
	@Bean
	public boolean CargaBancoOracle() {
		
		if (valor.equals("create")){
			serviceCarga.CargaBaseDatos();
		}
		
		return false;
	}
	
}
