package models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import enums.PerfilEnum;

public abstract class PessoasModel {
	
	protected Integer id;
	protected String nome;
	protected String email;
	protected String senha;
	protected LocalDate dataCadastro = LocalDate.now();
	protected Set<Integer> perfis = new HashSet<>();

	public PessoasModel() {
		super();
		addPerfil(PerfilEnum.CLIENTE);
	}

	public PessoasModel(Integer id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
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
