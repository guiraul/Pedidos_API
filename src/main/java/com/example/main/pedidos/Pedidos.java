package com.example.main.pedidos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
public class Pedidos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedidos;
	private String produtos;
	private double valor;
	private String endereco;
	
	public Pedidos(String produtos, double valor, String endereco) {
		this.produtos = produtos;
		this.valor = valor;
		this.endereco = endereco;
	}
	
	public int getId_pedidos() {
		return id_pedidos;
	}
	
	public void setId_pedidos(int id_pedidos) {
		this.id_pedidos = id_pedidos;
	}
	
	public String getProdutos() {
		return produtos;
	}
	
	public void setProdutos(String produtos) {
		this.produtos = produtos;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
