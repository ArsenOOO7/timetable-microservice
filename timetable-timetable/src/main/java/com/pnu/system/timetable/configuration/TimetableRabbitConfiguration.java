package com.pnu.system.timetable.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pnu.system.common.constant.TimetableProfiles.LOCAL;

@Configuration
@Profile(LOCAL)
public class TimetableRabbitConfiguration {

    @Value("${messaging.rabbit.queue.user.update}")
    private String userUpdateQueue;
    @Value("${messaging.rabbit.queue.user.delete}")
    private String userDeleteQueue;
    @Value("${messaging.rabbit.queue.subject.update}")
    private String subjectUpdateQueue;
    @Value("${messaging.rabbit.queue.subject.delete}")
    private String subjectDeleteQueue;
    @Value("${messaging.rabbit.queue.location.update}")
    private String locationUpdateQueue;
    @Value("${messaging.rabbit.queue.location.delete}")
    private String locationDeleteQueue;
    @Value("${messaging.rabbit.queue.group.update}")
    private String groupUpdateQueue;
    @Value("${messaging.rabbit.queue.group.delete}")
    private String groupDeleteQueue;

    @Bean
    public Queue timetableUserUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(userUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableUserDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(userDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableSubjectUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(subjectUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableSubjectDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(subjectDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableLocationUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(locationUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableLocationDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(locationDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableGroupUpdateQueue(AmqpAdmin admin) {
        Queue queue = new Queue(groupUpdateQueue, true);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue timetableGroupDeleteQueue(AmqpAdmin admin) {
        Queue queue = new Queue(groupDeleteQueue, true);
        admin.declareQueue(queue);
        return queue;
    }
}
