package com.helpdesk.fixly.reposistories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.fixly.models.PessoasModel;

public interface PessoasRepository extends JpaRepository<PessoasModel, Integer>{

}
