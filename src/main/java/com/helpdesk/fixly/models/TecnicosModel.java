package com.helpdesk.fixly.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.fixly.enums.PerfilEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "TECNICOS" )
public class TecnicosModel extends PessoasModel {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	List<ChamadosModel> chamados = new ArrayList<>();

	public TecnicosModel() {
		super();
		addPerfil(PerfilEnum.TECNICO);
	}

	public TecnicosModel(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(PerfilEnum.TECNICO);
	}

	public List<ChamadosModel> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadosModel> chamados) {
		this.chamados = chamados;
	}
	
	

}
