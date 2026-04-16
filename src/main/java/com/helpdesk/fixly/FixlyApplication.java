package com.helpdesk.fixly;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.helpdesk.fixly.Reposistories.ChamadosRepository;
import com.helpdesk.fixly.Reposistories.ClientesRepository;
import com.helpdesk.fixly.Reposistories.TecnicosRepository;
import com.helpdesk.fixly.enums.PrioridadeEnum;
import com.helpdesk.fixly.enums.StatusEnum;
import com.helpdesk.fixly.models.ChamadosModel;
import com.helpdesk.fixly.models.ClientesModel;
import com.helpdesk.fixly.models.TecnicosModel;

@SpringBootApplication
public class FixlyApplication  implements CommandLineRunner{

	@Autowired
	private ClientesRepository repoCliente;

	@Autowired
	private TecnicosRepository repoTecnico;

	@Autowired
	private ChamadosRepository repoChamado;
	
	public static void main(String[] args) {
		SpringApplication.run(FixlyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TecnicosModel t1 = new TecnicosModel(null,"Valdir Cesar","61293849","valdi.cersar@email.com","123");
	//	t1.addPerfil(PerfilEnum.ADMIN);
		
		ClientesModel cliente1 = new ClientesModel(null,"Linus Torvaldo","12393849","linus@email.com","321");
		
		ChamadosModel c1 = new ChamadosModel(null,"Chamado 01","Primeiro Chamado via Repository",PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO,t1,cliente1 );
		
		
		repoTecnico.save(t1);
		repoCliente.saveAll(Arrays.asList(cliente1));
		repoChamado.saveAll(Arrays.asList(c1));
	}

}
