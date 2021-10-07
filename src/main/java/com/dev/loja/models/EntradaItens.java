package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="entrada_itens")
public class EntradaItens implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @ManyToOne
    private EntradaProduto entrada;
    @Getter
    @Setter
    @ManyToOne
    private Produto produto;
    @Getter
    @Setter
    private Double quantidade = 0.0;
    @Getter
    @Setter
    private Double valorProduto = 0.0;
    @Getter
    @Setter
    private Double valorVenda = 0.0;


}