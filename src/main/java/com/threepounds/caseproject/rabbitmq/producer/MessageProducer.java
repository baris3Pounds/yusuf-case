package com.threepounds.caseproject.rabbitmq.producer;
import com.threepounds.caseproject.rabbitmq.model.Messages;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageProducer {

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingName;
    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private  String queueName;
    @Value("${spring.rabbitmq.template.exchange}")
    private  String exchange;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
        @PostConstruct
        public void init(){
        Messages messages=new Messages();
        messages.setId(UUID.randomUUID());
        messages.setName("advert");
        messages.setContent("advert updated");
        sendQueue(messages);

    }
    public void sendQueue(Messages messages){
        System.out.println(messages.getName());
        rabbitTemplate.convertAndSend(exchange,routingName,messages);
    }


}
