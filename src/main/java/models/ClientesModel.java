package models;

import java.util.ArrayList;
import java.util.List;

public class ClientesModel extends PessoasModel{
	
	List<ChamadosModel> chamados = new ArrayList<>();

	public ClientesModel() {
		super();
	}

	public ClientesModel(Integer id, String nome, String email, String senha) {
		super(id, nome, email, senha);
	}

	public List<ChamadosModel> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadosModel> chamados) {
		this.chamados = chamados;
	}
	
	
	
	

}
