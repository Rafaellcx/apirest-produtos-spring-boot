package com.produtos.apirest.models;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="TB_PRODUTO")
public class Produto extends RepresentationModel<Produto> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(columnDefinition = "serial")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tb_produto_id_seq")
//	@Column(name="id")
	@SequenceGenerator(name="id", sequenceName = "tb_produto_id_seq", allocationSize=50)
	private Long id;

	private String nome;
	
	private BigDecimal quantidade;
	
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}