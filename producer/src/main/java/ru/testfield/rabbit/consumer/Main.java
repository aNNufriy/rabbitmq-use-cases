package ru.testfield.rabbit.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Main {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.25.25.11");
        factory.setUsername("admin");
        factory.setPassword("admin");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            while (true){
                if(Thread.interrupted()){
                    break;
                }else{
                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
                    Thread.sleep(2000);
                }
            }
        }
    }
}