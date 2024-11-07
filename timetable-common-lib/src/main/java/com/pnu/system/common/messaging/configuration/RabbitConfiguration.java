package com.pnu.system.common.messaging.configuration;

import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.messaging.service.impl.RabbitPublisher;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.pnu.system.common.constant.TimetableProfiles.LOCAL;

@Configuration
@EnableRabbit
@Profile(LOCAL)
public class RabbitConfiguration {

    @Value("${messaging.rabbit.topic}")
    private String topic;

    @Bean
    @Primary
    public Exchange topicExchange(AmqpAdmin admin) {
        TopicExchange exchange = new TopicExchange(topic);
        admin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    @ConditionalOnMissingBean(Publisher.class)
    public Publisher rabbitPublisher(Exchange exchange, RabbitTemplate template) {
        return new RabbitPublisher(exchange, template);
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
