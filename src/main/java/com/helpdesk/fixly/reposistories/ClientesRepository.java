package com.helpdesk.fixly.reposistories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.fixly.models.ClientesModel;

public interface ClientesRepository  extends JpaRepository<ClientesModel, Integer>{

}
