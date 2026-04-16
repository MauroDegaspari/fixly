package com.helpdesk.fixly.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.enums.PerfilEnum;
import com.helpdesk.fixly.enums.PrioridadeEnum;
import com.helpdesk.fixly.enums.StatusEnum;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.models.ClientesModel;
import com.helpdesk.fixly.models.TecnicosModel;
import com.helpdesk.fixly.reposistories.ChamadosRepository;
import com.helpdesk.fixly.reposistories.ClientesRepository;
import com.helpdesk.fixly.reposistories.TecnicosRepository;

@Service
public class CargaDadosBaseService {
	
	@Autowired
	private ClientesRepository repoCliente;
	@Autowired
	private TecnicosRepository repoTecnico;
	@Autowired
	private ChamadosRepository repoChamado;
	

	public void CargaBaseDatos() {

		TecnicosModel t1 = new TecnicosModel(null,"Valdir Cesar","61293849","valdi.cersar@email.com","123");
		t1.addPerfil(PerfilEnum.ADMIN);
		
		ClientesModel cliente1 = new ClientesModel(null,"Linus Torvaldo","12393849","linus@email.com","321");
		
		ChamadosModel c1 = new ChamadosModel(null,"Chamado 01","Primeiro Chamado via Repository",PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO,t1,cliente1 );
		
		
		repoTecnico.save(t1);
		repoCliente.saveAll(Arrays.asList(cliente1));	
		repoChamado.saveAll(Arrays.asList(c1));
	}

}
