package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="produtos")
public class Produto implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Double valorVenda;
    @Getter
    @Setter
    private String categoria;
    @Getter
    @Setter
    private String marca;
    @Getter
    @Setter
    private Double quantidadeEstoque = 0.0;
    @Getter
    @Setter
    private String nomeImagem; // TODO: criar entidade imagem amarzenando mais de um path

}