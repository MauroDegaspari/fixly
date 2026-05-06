package com.helpdesk.fixly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.fixly.dtos.ChamadosDto;
import com.helpdesk.fixly.services.ChamadosService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {
	
	@Autowired
	private ChamadosService service;
	
	
	@GetMapping
	public ResponseEntity<ChamadosDto> todosChamados(){
		
		//ChamadosModel chamados = new ChamadosModel() service.todosChamados();
		
		return null;
	}

}
