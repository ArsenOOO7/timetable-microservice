package com.pnu.system.lessonlocation.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.lessonlocation.event.model.LessonLocationCreateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationDeleteEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationTypeUpdateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationUpdateEvent;
import com.pnu.system.lessonlocation.service.LessonLocationSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonLocationEventHandler {

    private final Publisher publisher;
    private final LessonLocationSnapshotService service;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onLessonLocationCreate(LessonLocationCreateEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, EntityMessageType.LESSON_LOCATION_UPDATE, service.getById(event.getLocation().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onLessonLocationUpdate(LessonLocationUpdateEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, EntityMessageType.LESSON_LOCATION_UPDATE, service.getById(event.getLocation().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onLessonLocationTypeUpdate(LessonLocationTypeUpdateEvent event) {
        List<LessonLocationSnapshotDto> lessonLocations = service.getByLocationTypeId(event.getType().getId());
        lessonLocations.forEach(lessonLocation -> publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, EntityMessageType.LESSON_LOCATION_UPDATE, lessonLocation));
    }

    @TransactionalEventListener
    public void onLessonLocationDelete(LessonLocationDeleteEvent event) {
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_DELETE_ROUTING_KEY, EntityMessageType.LESSON_LOCATION_DELETE, event.getId());
    }
}
