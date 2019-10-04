package com.example.pedidos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class PedidosService {
	
	private final static String QUEUE_NAME = "pedidos";
	
	@Autowired
    PedidoRepository pedidoRepository;

    public List<Pedidos> getAllPedidos() {
        List<Pedidos> persons = new ArrayList<Pedidos>();
        pedidoRepository.findAll().forEach(person -> persons.add(person));
        return persons;
    }

    public Pedidos getPedidoById(int id) {
        return pedidoRepository.findById(id).get();
    }

    public void saveOrUpdate(Pedidos pedido) {
        pedidoRepository.save(pedido);
    }

    public void delete(int id) {
        pedidoRepository.deleteById(id);
    }
    
    public String menssagemPedidoRealizado(Pedidos pedido) {
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = ""+pedido.getId();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }catch(Exception e) {
        	return "Pedido não pode ser realizado!";
        }
		
		return "Pedido Realizado com Sucesso!";
	}
}
