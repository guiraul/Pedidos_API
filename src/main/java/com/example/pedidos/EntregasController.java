package com.example.pedidos;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntregasController {
	
	@Autowired
    EntregasService entregas;
	
	@PostMapping("/entrega")
    private String saveEntrega() {
		
		try {
			entregas.verificaFilaPedido();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Entrega";
    }
}
