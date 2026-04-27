package com.helpdesk.fixly.reposistories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.fixly.models.PessoasModel;

public interface PessoasRepository extends JpaRepository<PessoasModel, Integer>{
	
	Optional<PessoasModel> findByCpf(String cpf);
	Optional<PessoasModel> findByEmail(String email);

}
