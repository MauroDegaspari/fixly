package com.helpdesk.fixly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.dtos.TecnicoDto;
import com.helpdesk.fixly.exceptions.NotFoundException;
import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.reposistories.TecnicosRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicosRepository repo;
	
	public TecnicosModel AcharTecnicoId(Integer id) {
		Optional<TecnicosModel> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new  NotFoundException("Tecnico não encontrado pelo ID: "+ id));
	}
	
	public List<TecnicosModel> AcharTodos() {
		List<TecnicosModel> obj = repo.findAll();
		
		return obj;
	}
	
	public TecnicosModel create(TecnicoDto tecnico) {
		TecnicosModel tec = new TecnicosModel(tecnico);
		
		return repo.save(tec);
	}
}
