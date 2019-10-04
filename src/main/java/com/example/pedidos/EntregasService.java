package com.example.pedidos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class EntregasService {
	
	private final static String QUEUE_NAME = "pedidos";
	
	@Autowired
    EntregaRepository entregaRepository;

    public List<Entregas> getAllEntregas() {
        List<Entregas> persons = new ArrayList<Entregas>();
        entregaRepository.findAll().forEach(person -> persons.add(person));
        return persons;
    }

    public Entregas getEntregaById(int id) {
        return entregaRepository.findById(id).get();
    }

    public void saveOrUpdate(int pedidoId) {
    	System.out.println(pedidoId);
    	PedidosService pedidoServico = new PedidosService();
    	
    	Pedidos pedido = pedidoServico.getPedidoById(pedidoId);
    	
    	Entregas entrega = new Entregas();
    	
    	entrega.setId(pedidoId);
    	entrega.setId_pedido(pedidoId);
    	entrega.setEndereco(pedido.getEndereco());
    	
        entregaRepository.save(entrega);
    }

    public void delete(int id) {
        entregaRepository.deleteById(id);
    }
    
    public void verificaFilaPedido() throws IOException, TimeoutException {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            saveOrUpdate(Integer.parseInt(message));
            
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
