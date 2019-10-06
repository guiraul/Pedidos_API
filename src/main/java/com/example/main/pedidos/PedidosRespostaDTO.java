package com.example.main.pedidos;

public class PedidosRespostaDTO {
	
	private int id_pedidos;
	private String produtos;
	private double valor;
	private String endereco;
	
	public PedidosRespostaDTO(int id_pedidos, String produtos, double valor, String endereco) {
		this.id_pedidos = id_pedidos;
		this.produtos = produtos;
		this.valor = valor;
		this.endereco = endereco;
	}

	public static PedidosRespostaDTO transformaEmDTO(Pedidos pedido) {
		
		return new PedidosRespostaDTO(pedido.getId_pedidos(), pedido.getProdutos(), pedido.getValor(), pedido.getEndereco());
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
