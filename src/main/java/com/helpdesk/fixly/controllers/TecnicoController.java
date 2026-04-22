package com.helpdesk.fixly.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helpdesk.fixly.dtos.TecnicoDto;
import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.services.TecnicoService;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
		TecnicosModel obj = tecService.AcharTecnicoId(id);
		
		return ResponseEntity.ok().body(new TecnicoDto(obj));
		
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll(){
		
		List<TecnicosModel> listObj = tecService.AcharTodos();
		List<TecnicoDto> listDto = listObj.stream().map(x -> new TecnicoDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
}