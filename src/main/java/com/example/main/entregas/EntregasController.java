package com.example.main.entregas;

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
    private void salvarEntrega() {
		
		try {
			entregas.verificaFilaPedido();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
