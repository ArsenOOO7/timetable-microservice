package com.pnu.system.academiccatalog.event.handler;

import com.pnu.system.academiccatalog.event.model.SubjectCreateEvent;
import com.pnu.system.academiccatalog.event.model.SubjectDeleteEvent;
import com.pnu.system.academiccatalog.event.model.SubjectUpdateEvent;
import com.pnu.system.academiccatalog.mapper.SubjectMessagingMapper;
import com.pnu.system.academiccatalog.service.SubjectSnapshotService;
import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SubjectEventHandler {

    private final Publisher publisher;
    private final SubjectMessagingMapper mapper;
    private final SubjectSnapshotService service;

    @TransactionalEventListener
    public void onSubjectCreate(SubjectCreateEvent event) {
        EntityUpdateMessage<SubjectSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getSubject().getId()), EntityMessageType.SUBJECT);
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onSubjectUpdate(SubjectUpdateEvent event) {
        EntityUpdateMessage<SubjectSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getSubject().getId()), EntityMessageType.SUBJECT);
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onSubjectDelete(SubjectDeleteEvent event) {
        EntityDeleteMessage message = mapper.asEntityDeleteMessage(event.getId(), EntityMessageType.SUBJECT);
        publisher.send(RabbitRoutingKey.SUBJECT_DELETE_ROUTING_KEY, message);
    }
}
