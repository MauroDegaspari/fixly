package com.helpdesk.fixly.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.fixly.dtos.ClienteDto;
import com.helpdesk.fixly.enums.PerfilEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "CLIENTES" )
public class ClientesModel extends PessoasModel{	
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	List<ChamadosModel> chamados = new ArrayList<>();

	public ClientesModel() {
		super();
		addPerfil(PerfilEnum.CLIENTE);
	}

	public ClientesModel(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(PerfilEnum.CLIENTE);
	}
	
	public ClientesModel(ClienteDto tec) {
		super();
		this.id = tec.getId();
		this.nome = tec.getNome();
		this.cpf = tec.getCpf();
		this.email = tec.getEmail();
		this.senha = tec.getSenha();
		this.perfis = tec.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCadastro = tec.getDataCadastro();
	}


	public List<ChamadosModel> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadosModel> chamados) {
		this.chamados = chamados;
	}
	
	
	
	

}
