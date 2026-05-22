package com.helpdesk.fixly.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.dtos.ChamadosDto;
import com.helpdesk.fixly.enums.PrioridadeEnum;
import com.helpdesk.fixly.enums.StatusEnum;
import com.helpdesk.fixly.exceptions.NotFoundException;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.models.ClientesModel;
import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.reposistories.ChamadosRepository;

import jakarta.validation.Valid;

@Service
public class ChamadosService {

	@Autowired
	private ChamadosRepository repo;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	public ChamadosModel acharChamado(Integer id) {
		Optional<ChamadosModel> chamadoId = repo.findById(id);
		return chamadoId.orElseThrow(() -> new  NotFoundException("Chamado não encontrado pelo ID: "+ id));
	}
	
	public List<ChamadosModel> todosChamados(){
		
		List<ChamadosModel> todosChamados = repo.findAll();
		
		return todosChamados;
	}

	public ChamadosModel criarChamado(ChamadosDto objDto) {
		return repo.save(criarOuAtualizarChamado(objDto));
	}
	
	public ChamadosModel atualizaChamado(Integer id, ChamadosDto objDto) {
		objDto.setId(id);
		
		ChamadosModel atualizar = acharChamado(id) ;
		atualizar = criarChamado(objDto);
		
		return repo.save(atualizar);
	}
	
	private ChamadosModel criarOuAtualizarChamado(ChamadosDto dto) {
		TecnicosModel tecnico = tecnicoService.AcharTecnicoId(dto.getTecnico());
		ClientesModel cliente = clienteService.AcharClienteId(dto.getCliente());
		
		ChamadosModel chamado = new ChamadosModel();
		
		if(dto.getId() != null) {
			chamado.setId(dto.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridades(PrioridadeEnum.toEnums(dto.getPrioridades()));
		chamado.setStatus(StatusEnum.toEnums(dto.getStatus()));
		chamado.setTitulo(dto.getTitulo());
		chamado.setObservacao(dto.getObservacao());
		
		if (dto.getStatus() == 2) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		return chamado;
	}


	
}
