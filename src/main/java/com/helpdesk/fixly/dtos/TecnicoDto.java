package com.helpdesk.fixly.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.fixly.enums.PerfilEnum;
import com.helpdesk.fixly.models.TecnicosModel;

import jakarta.validation.constraints.NotNull;

public class TecnicoDto {
	
	
	protected Integer id;
	
	@NotNull(message = "Nome deve ser Registrado")
	protected String nome;
	
	@NotNull(message = "Cpf deve ser Registrado")	
	protected String cpf;
	
	@NotNull(message = "Email deve ser Registrado")
	protected String email;
	
	@NotNull(message = "Senha deve ser Registrado")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCadastro = LocalDate.now();

	public TecnicoDto() {
		super();
		addPerfis(PerfilEnum.TECNICO);
	}

	public TecnicoDto(TecnicosModel tec) {
		super();
		this.id = tec.getId();
		this.nome = tec.getNome();
		this.cpf = tec.getCpf();
		this.email = tec.getEmail();
		this.senha = tec.getSenha();
		this.perfis = tec.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCadastro = tec.getDataCadastro();
		addPerfis(PerfilEnum.TECNICO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(x -> PerfilEnum.toEnums(x)).collect(Collectors.toSet());
	}

	public void addPerfis(PerfilEnum perfis) {
		this.perfis.add(perfis.getCodigo());
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	

}
