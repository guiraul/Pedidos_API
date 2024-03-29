package com.example.main.entregas;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.pedidos.Pedidos;
import com.example.main.pedidos.PedidosService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class EntregasService {
	
	private final static String QUEUE_NAME = "pedidos";
	
	@Autowired
    EntregaRepository entregaRepository;
	
	@Autowired
	PedidosService pedidoServico;

    public void salvar(int pedidoId) {
    	
    	Pedidos pedido = pedidoServico.getPedidoById(pedidoId);
    	
    	Entregas entrega = new Entregas();
    	
    	entrega.setId_pedido(pedidoId);
    	entrega.setEndereco(pedido.getEndereco());
    	
        entregaRepository.save(entrega);
    }
    
    public void verificaFilaPedido() throws IOException, TimeoutException {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" Serviço iniciado, esperando Mensagens. Para sair pressione CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody(), "UTF-8");
            System.out.println(" Pedido '" + mensagem + "' Recebido!");
            salvar(Integer.parseInt(mensagem));
            
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
