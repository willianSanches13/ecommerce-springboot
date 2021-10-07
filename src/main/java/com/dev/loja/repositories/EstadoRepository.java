package com.dev.loja.repositories;
import com.dev.loja.models.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    public Iterable<Estado> findByNomeContainingIgnoreCase(String nome);

}