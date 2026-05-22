package com.helpdesk.fixly.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helpdesk.fixly.dtos.ClienteDto;
import com.helpdesk.fixly.models.ClientesModel;
import com.helpdesk.fixly.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
		ClientesModel obj = clienteService.AcharClienteId(id);
		
		return ResponseEntity.ok().body(new ClienteDto(obj));
		
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll(){
		
		List<ClientesModel> listObj = clienteService.AcharTodos();
		List<ClienteDto> listDto = listObj.stream().map(x -> new ClienteDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objTCliente){
		ClientesModel newObj = clienteService.create(objTCliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return  ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> update(@Valid @PathVariable Integer id, @RequestBody ClienteDto objTecnico ){
		ClientesModel novoTecnico = clienteService.Atualizar(id, objTecnico);
		
		return ResponseEntity.ok().body(new ClienteDto(novoTecnico));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
		clienteService.deleteCliente(id);
		
		return ResponseEntity.noContent().build();
	}
	
}