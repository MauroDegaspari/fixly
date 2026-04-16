package com.helpdesk.fixly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.reposistories.TecnicosRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicosRepository repo;
	
	public TecnicosModel AcharTecnicoId(Integer id) {
		Optional<TecnicosModel> obj = repo.findById(id);
		
		return obj.orElse(null);
	}
}
