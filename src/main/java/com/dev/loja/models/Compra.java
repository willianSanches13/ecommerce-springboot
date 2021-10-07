package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="compra")
public class Compra implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @ManyToOne
    private Cliente cliente;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra = new Date();

    @Setter
    private Double valorTotal = 0.0;
    public Double getValorTotal(){
        if(this.valorTotal == null){
            valorTotal = 0.0;
        }
        return this.valorTotal;
    }
}