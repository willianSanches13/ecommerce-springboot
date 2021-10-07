package com.dev.loja.repositories;
import com.dev.loja.models.Papel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PapelRepository extends JpaRepository<Papel, Integer> {

    public Iterable<Papel> findByNomeContainingIgnoreCase(String nome);

}