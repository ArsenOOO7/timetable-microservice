package com.pnu.system.laboratorycontrol.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pnu.system.common.constant.TimetableProfiles.LOCAL;

@Configuration
@Profile(LOCAL)
public class LaboratoryControlRabbitConfiguration {

    @Value("${messaging.rabbit.queue.user.update}")
    private String userUpdateQueue;
    @Value("${messaging.rabbit.queue.user.delete}")
    private String userDeleteQueue;
    @Value("${messaging.rabbit.queue.subject.update}")
    private String subjectUpdateQueue;
    @Value("${messaging.rabbit.queue.subject.delete}")
    private String subjectDeleteQueue;
    @Value("${messaging.rabbit.queue.group.update}")
    private String groupUpdateQueue;
    @Value("${messaging.rabbit.queue.group.delete}")
    private String groupDeleteQueue;

    @Bean
    public Queue laboratoryControlUserUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(userUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue laboratoryControlUserDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(userDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue laboratoryControlSubjectUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(subjectUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue laboratoryControlSubjectDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(subjectDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue laboratoryControlGroupUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(groupUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue laboratoryControlGroupDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(groupDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }
}
