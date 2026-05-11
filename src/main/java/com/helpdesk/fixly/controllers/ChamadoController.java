package com.helpdesk.fixly.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helpdesk.fixly.dtos.ChamadosDto;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.services.ChamadosService;

import jakarta.validation.Valid;

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
	
	@PostMapping
	public ResponseEntity<ChamadosDto> createChamado(@Valid @RequestBody ChamadosDto objDto){
		ChamadosModel obj = service.criarChamado(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadosDto> updateChamado(Integer id, @Valid @RequestBody ChamadosDto objDto){
		ChamadosModel atualizaChamado = service.atualizaChamado(id, objDto);
		
		return ResponseEntity.ok().body(new ChamadosDto(atualizaChamado));
	}

}
