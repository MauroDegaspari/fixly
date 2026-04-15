package models;

import java.time.LocalDate;
import java.util.Objects;

import enums.PrioridadeEnum;
import enums.StatusEnum;

public class ChamadosModel {
	
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private String titulo;
	private String observacao;
	
	private PrioridadeEnum prioridades;
	private StatusEnum status;
	
	private TecnicosModel tecnico;
	private ClientesModel cliente;
	
	public ChamadosModel() {
		super();
	}

	public ChamadosModel(Integer id, String titulo, String observacao, PrioridadeEnum prioridades, StatusEnum status,
			TecnicosModel tecnico, ClientesModel cliente) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.observacao = observacao;
		this.prioridades = prioridades;
		this.status = status;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PrioridadeEnum getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(PrioridadeEnum prioridades) {
		this.prioridades = prioridades;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public TecnicosModel getTecnico() {
		return tecnico;
	}

	public void setTecnico(TecnicosModel tecnico) {
		this.tecnico = tecnico;
	}

	public ClientesModel getCliente() {
		return cliente;
	}

	public void setCliente(ClientesModel cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamadosModel other = (ChamadosModel) obj;
		return Objects.equals(id, other.id);
	}	
	

}
