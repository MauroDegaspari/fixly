package com.helpdesk.fixly.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.fixly.models.ChamadosModel;

import jakarta.validation.constraints.NotNull;

public class ChamadosDto implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	private String observacao;	
	@NotNull(message = "Campo Titulo é obrigatorio")
	private String titulo;
	@NotNull(message = "Campo Prioridade é obrigatorio")
	private Integer prioridades;
	@NotNull(message = "Campo Status é obrigatorio")
	private Integer status;
	@NotNull(message = "Campo Tecnico é obrigatorio")
	private Integer tecnico;
	@NotNull(message = "Campo Cliente é obrigatorio")
	private Integer cliente;
	private String nomeTecnico;
	private String nomeCliente;
	
	
	public ChamadosDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ChamadosDto(ChamadosModel chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.titulo = chamado.getTitulo();
		this.observacao = chamado.getObservacao();
		this.prioridades = chamado.getPrioridades().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.tecnico = chamado.getTecnico().getId();
		this.cliente = chamado.getCliente().getId();
		this.nomeCliente = chamado.getCliente().getNome();
		this.nomeTecnico = chamado.getTecnico().getNome();
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


	public Integer getPrioridades() {
		return prioridades;
	}


	public void setPrioridades(Integer prioridades) {
		this.prioridades = prioridades;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getTecnico() {
		return tecnico;
	}


	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}


	public String getNomeTecnico() {
		return nomeTecnico;
	}


	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}


	public Integer getCliente() {
		return cliente;
	}


	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	
	
	
	
	

}
