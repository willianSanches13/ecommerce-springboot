package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="estado")
public class Estado implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String sigla;
}