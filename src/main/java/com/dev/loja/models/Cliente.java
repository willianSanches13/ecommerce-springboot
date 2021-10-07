package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String senha;
    @Getter
    @Setter
    @ManyToOne
    private Cidade cidade;
}