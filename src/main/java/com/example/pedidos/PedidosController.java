package com.example.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosController {
	
	@Autowired
    PedidosService pedidoRealizado;
	
	@PostMapping("/pedido")
    private String savePedido(@RequestBody Pedidos pedido) {
		pedidoRealizado.saveOrUpdate(pedido);
		
		return pedidoRealizado.menssagemPedidoRealizado(pedido);
    }
}
