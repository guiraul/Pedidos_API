package com.example.main.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosController {
	
	@Autowired
    PedidosService pedidoRealizado;
	
	@PostMapping("/pedido")
    private ResponseEntity<PedidosRespostaDTO> salvarPedido(@RequestBody PedidosDTO dto) {
		
		Pedidos pedido = pedidoRealizado.salvar(dto.transformaParaObjeto(dto));
		pedidoRealizado.menssagemPedidoRealizado(pedido);
		
		return new ResponseEntity<>(PedidosRespostaDTO.transformaEmDTO(pedido), HttpStatus.CREATED);
    }
}
