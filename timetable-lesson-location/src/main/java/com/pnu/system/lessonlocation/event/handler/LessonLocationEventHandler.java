package com.pnu.system.lessonlocation.event.handler;

import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.lessonlocation.event.model.LessonLocationCreateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationDeleteEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationUpdateEvent;
import com.pnu.system.lessonlocation.mapper.LessonLocationMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class LessonLocationEventHandler {

    private final Publisher publisher;
    private final LessonLocationMessageMapper mapper;

    @TransactionalEventListener
    public void onLessonLocationCreate(LessonLocationCreateEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, mapper.asLessonLocationUpdateMessage(event.getLocation()));
    }

    @TransactionalEventListener
    public void onLessonLocationUpdate(LessonLocationUpdateEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, mapper.asLessonLocationUpdateMessage(event.getLocation()));
    }

    @TransactionalEventListener
    public void onLessonLocationDelete(LessonLocationDeleteEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_DELETE_ROUTING_KEY, mapper.asLessonLocationDeleteMessage(event.getId()));
    }
}
