package com.helpdesk.fixly.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.fixly.dtos.ChamadosDto;
import com.helpdesk.fixly.dtos.ClienteDto;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.services.ChamadosService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {
	
	@Autowired
	private ChamadosService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadosDto> chamadoById(@PathVariable Integer id){
		
		ChamadosModel chamadoId = service.acharChamado(id);
		
		return ResponseEntity.ok().body(new ChamadosDto(chamadoId	));
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<ChamadosDto>> todosChamados(){
		
		List<ChamadosModel> chamados =  service.todosChamados();
		List<ChamadosDto> chamaddosDto = chamados.stream().map(x -> new ChamadosDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(chamaddosDto);
	}

}
