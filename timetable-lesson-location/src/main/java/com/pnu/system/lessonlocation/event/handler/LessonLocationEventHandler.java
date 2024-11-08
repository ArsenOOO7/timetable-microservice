package com.pnu.system.lessonlocation.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.lessonlocation.event.model.LessonLocationCreateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationDeleteEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationTypeUpdateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationUpdateEvent;
import com.pnu.system.lessonlocation.mapper.LessonLocationMessageMapper;
import com.pnu.system.lessonlocation.service.LessonLocationSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonLocationEventHandler {

    private final Publisher publisher;
    private final LessonLocationMessageMapper mapper;
    private final LessonLocationSnapshotService service;

    @TransactionalEventListener
    public void onLessonLocationCreate(LessonLocationCreateEvent event) {
        EntityUpdateMessage<LessonLocationSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getLocation().getId()), EntityMessageType.LESSON_LOCATION);
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onLessonLocationUpdate(LessonLocationUpdateEvent event) {
        EntityUpdateMessage<LessonLocationSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getLocation().getId()), EntityMessageType.LESSON_LOCATION);
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onLessonLocationTypeUpdate(LessonLocationTypeUpdateEvent event) {
        List<EntityUpdateMessage<LessonLocationSnapshotDto>> messages = mapper.asEntityUpdateMessages(service.getByLocationTypeId(event.getType().getId()), EntityMessageType.LESSON_LOCATION);
        messages.forEach(message -> publisher.send(RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY, message));
    }

    @TransactionalEventListener
    public void onLessonLocationDelete(LessonLocationDeleteEvent event) {
        EntityDeleteMessage message = mapper.asEntityDeleteMessage(event.getId(), EntityMessageType.LESSON_LOCATION);
        publisher.send(RabbitRoutingKey.LESSON_LOCATION_DELETE_ROUTING_KEY, message);
    }
}
