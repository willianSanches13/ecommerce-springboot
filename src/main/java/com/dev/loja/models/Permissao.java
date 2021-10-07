package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="permissoes")
public class Permissao implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date dataCadastro = new Date();
    @Setter
    @Getter
    @ManyToOne
    private Funcionario funcionario;
    @Getter
    @Setter
    @ManyToOne
    private Papel papel;

}