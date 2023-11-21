package com.threepounds.caseproject.rabbitmq.listener;
import com.threepounds.caseproject.rabbitmq.model.Messages;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class MessageListener  {
    @RabbitListener(queues = "spring-boot-queue")
    public void handleMessage(Messages messages){
        System.out.println(messages.toString());
        System.out.println("mesaj gitti");

    }
 }




