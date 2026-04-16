package com.helpdesk.fixly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.services.TecnicoService;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicosModel> findById(@PathVariable Integer id){
		TecnicosModel obj = tecService.AcharTecnicoId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
}