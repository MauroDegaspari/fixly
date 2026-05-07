package com.helpdesk.fixly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.exceptions.NotFoundException;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.reposistories.ChamadosRepository;

@Service
public class ChamadosService {

	@Autowired
	private ChamadosRepository repo;
	
	public ChamadosModel acharChamado(Integer id) {
		Optional<ChamadosModel> chamadoId = repo.findById(id);
		return chamadoId.orElseThrow(() -> new  NotFoundException("Chamado não encontrado pelo ID: "+ id));
	}
	
	public List<ChamadosModel> todosChamados(){
		
		List<ChamadosModel> todosChamados = repo.findAll();
		
		return todosChamados;
	}

	
}
