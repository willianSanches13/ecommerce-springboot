package com.dev.loja.repositories;
import com.dev.loja.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    public Iterable<Cidade> findByNomeContainingIgnoreCase(String nome);

}