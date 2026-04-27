package com.helpdesk.fixly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.dtos.TecnicoDto;
import com.helpdesk.fixly.exceptions.DataIntegrityViolationException;
import com.helpdesk.fixly.exceptions.NotFoundException;
import com.helpdesk.fixly.models.PessoasModel;
import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.reposistories.PessoasRepository;
import com.helpdesk.fixly.reposistories.TecnicosRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicosRepository repo;
	
	@Autowired
	private PessoasRepository PRepo;
	
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
		
		validarCpfEEmail(tecnico);
		
		return repo.save(tec);
	}
	
	private void validarCpfEEmail(TecnicoDto tecnicoParam) {
		Optional<PessoasModel> objPessoasCpf = PRepo.findByCpf(tecnicoParam.getCpf());
		Optional<PessoasModel> objPessoasEmail = PRepo.findByEmail(tecnicoParam.getEmail());
		
		if (objPessoasCpf.isPresent() && objPessoasCpf.get().getId() != tecnicoParam.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastro no Id: "+ objPessoasCpf.get().getId());
		}else if (objPessoasEmail.isPresent() && objPessoasEmail.get().getId() != tecnicoParam.getId()) {
			throw new DataIntegrityViolationException("Email já cadastro no Id: "+ objPessoasEmail.get().getId());
		}
	}
	
}
