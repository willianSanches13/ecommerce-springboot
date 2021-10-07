package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="entrada_produto")
public class EntradaProduto implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @ManyToOne
    private Funcionario funcionario;
    @Getter
    @Setter
    private Date dataEntrada = new Date();
    @Getter
    @Setter
    private String observacao;
    @Getter
    @Setter
    private String fornecedor;

}