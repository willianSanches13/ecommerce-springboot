package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="cidades")
public class Cidade implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Getter
    private String nome;
    @Getter
    @Setter
    @ManyToOne
    private Estado estado;
    private String nomeSigla;

    public String getNomeSigla(){
        return this.nome + "-" + this.estado.getSigla();
    }
}