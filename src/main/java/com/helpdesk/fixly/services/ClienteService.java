package com.helpdesk.fixly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.dtos.ClienteDto;
import com.helpdesk.fixly.exceptions.DataIntegrityViolationException;
import com.helpdesk.fixly.exceptions.NotFoundException;
import com.helpdesk.fixly.models.ClientesModel;
import com.helpdesk.fixly.models.PessoasModel;
import com.helpdesk.fixly.reposistories.ClientesRepository;
import com.helpdesk.fixly.reposistories.PessoasRepository;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClientesRepository repo;
	
	@Autowired
	private PessoasRepository PRepo;
	
	public ClientesModel AcharClienteId(Integer id) {
		Optional<ClientesModel> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new  NotFoundException("Cliente não encontrado pelo ID: "+ id));
	}
	
	public List<ClientesModel> AcharTodos() {
		List<ClientesModel> obj = repo.findAll();
		
		return obj;
	}
	
	public ClientesModel create(ClienteDto salvaCliente) {
		ClientesModel cliente = new ClientesModel(salvaCliente);
		
		validarCpfEEmail(salvaCliente);
		
		return repo.save(cliente);
	}
	

	public ClientesModel Atualizar(@Valid Integer id, ClienteDto objTecnico) {
		
		objTecnico.setId(id);// Evita falha de segurança.
		
		ClientesModel novoTec = AcharClienteId(id);
		validarCpfEEmail(objTecnico);
		novoTec = new ClientesModel(objTecnico);
		
		
		return repo.save(novoTec);
	}
	
	public void deleteCliente(Integer id) {
		ClientesModel tec = AcharClienteId(id);
		
		if(!tec.getChamados().isEmpty()) {
			throw new DataIntegrityViolationException("Tecnico possui chamados, não pode ser Deletado.");
		}
		
		repo.deleteById(id);
	}
	private void validarCpfEEmail(ClienteDto tecnicoParam) {
		Optional<PessoasModel> objPessoasCpf = PRepo.findByCpf(tecnicoParam.getCpf());
		Optional<PessoasModel> objPessoasEmail = PRepo.findByEmail(tecnicoParam.getEmail());
		
		if (objPessoasCpf.isPresent() && objPessoasCpf.get().getId() != tecnicoParam.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastro no Id: "+ objPessoasCpf.get().getId());
		}else if (objPessoasEmail.isPresent() && objPessoasEmail.get().getId() != tecnicoParam.getId()) {
			throw new DataIntegrityViolationException("Email já cadastro no Id: "+ objPessoasEmail.get().getId());
		}
	}

}
