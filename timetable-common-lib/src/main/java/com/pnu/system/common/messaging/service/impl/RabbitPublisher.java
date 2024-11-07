package com.pnu.system.common.messaging.service.impl;

import com.pnu.system.common.messaging.service.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class RabbitPublisher implements Publisher {

    private final Exchange exchange;
    private final RabbitTemplate template;

    @Override
    public void send(String routingKey, Object message) {
        template.convertAndSend(exchange.getName(), routingKey, message);
    }
}
