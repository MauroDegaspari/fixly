package com.helpdesk.fixly.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.fixly.enums.PerfilEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Pessoa")
public abstract class PessoasModel implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String nome;
	
	@Column(unique = true)
	protected String cpf;
	protected String email;

	@Column(unique = true)
	protected String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCadastro = LocalDate.now();
	
	@ElementCollection(fetch = FetchType.EAGER) // Para assegurar que a lista de perfis venha junsto com o Usuario. 
	@CollectionTable(name = "PERFIS") //criação de tabela no banco para perfis
	protected Set<Integer> perfis = new HashSet<>();

	public PessoasModel() {
		super();
		addPerfil(PerfilEnum.CLIENTE);
	}

	public PessoasModel(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(PerfilEnum.CLIENTE);
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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(x -> PerfilEnum.toEnums(x)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilEnum perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoasModel other = (PessoasModel) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
	

}
