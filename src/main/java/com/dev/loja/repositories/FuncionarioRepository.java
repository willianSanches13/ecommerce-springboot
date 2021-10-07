package com.dev.loja.repositories;
import com.dev.loja.models.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	

}