package models;

import java.util.ArrayList;
import java.util.List;

public class TecnicosModel extends PessoasModel{
	
	List<ChamadosModel> chamados = new ArrayList<>();

	public TecnicosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TecnicosModel(Integer id, String nome, String email, String senha) {
		super(id, nome, email, senha);
		// TODO Auto-generated constructor stub
	}

	public List<ChamadosModel> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadosModel> chamados) {
		this.chamados = chamados;
	}
	
	

}
