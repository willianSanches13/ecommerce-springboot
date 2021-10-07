package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="itens_compra")
public class ItensCompra implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @ManyToOne
    private Produto produto;
    @Getter
    @Setter
    @ManyToOne
    private Compra compra;
    @Getter
    @Setter
    private Integer quantidade = 0;
    @Setter
    private Double valorUnitario = 0.0;
    @Getter
    @Setter
    private Double valorTotal = 0.0;
    public Double getValorUnitario(){
        if(valorUnitario == null){
            this.valorUnitario = 0.0;
        }
        return this.valorUnitario;
    }
}