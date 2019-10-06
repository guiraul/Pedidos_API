package com.example.main.pedidos;

public class PedidosDTO {
	
	private String produtos;
	private double valor;
	private String endereco;
	
	public Pedidos transformaParaObjeto(PedidosDTO dto) {
		
		return new Pedidos(dto.getProdutos(), dto.getValor(), dto.getEndereco());
	}

	public String getProdutos() {
		return produtos;
	}

	public double getValor() {
		return valor;
	}

	public String getEndereco() {
		return endereco;
	}
	
	
}
