package com.example.main.entregas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Entregas {

	@Id
    @GeneratedValue
    private int id_entregas;
    private int id_pedido;
	private String endereco;
	
	
	public int getId_entregas() {
		return id_entregas;
	}
	
	public void setId_entregas(int id_entregas) {
		this.id_entregas = id_entregas;
	}
	
	public int getId_pedido() {
		return id_pedido;
	}
	
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
