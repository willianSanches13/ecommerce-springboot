package com.dev.loja.models;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private Double salarioBruto;
	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	private Date dataEntrada = new Date();
	@Temporal(TemporalType.DATE)
	private Date dataSaida;
	private String cargo;
	@ManyToOne
	private Cidade cidade;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String uf;
	private String cep;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String senha;

	public Funcionario (){
		super();
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public String getCargo() {
		return cargo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getUf() {
		return uf;
	}

	public String getCep() {
		return cep;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}

