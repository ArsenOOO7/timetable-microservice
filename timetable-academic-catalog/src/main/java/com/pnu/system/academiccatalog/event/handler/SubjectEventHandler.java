package com.pnu.system.academiccatalog.event.handler;

import com.pnu.system.academiccatalog.event.model.SubjectCreateEvent;
import com.pnu.system.academiccatalog.event.model.SubjectDeleteEvent;
import com.pnu.system.academiccatalog.event.model.SubjectUpdateEvent;
import com.pnu.system.academiccatalog.service.SubjectSnapshotService;
import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SubjectEventHandler {

    private final Publisher publisher;
    private final SubjectSnapshotService service;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onSubjectCreate(SubjectCreateEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, EntityMessageType.SUBJECT_UPDATE, service.getById(event.getSubject().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onSubjectUpdate(SubjectUpdateEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, EntityMessageType.SUBJECT_UPDATE, service.getById(event.getSubject().getId()));
    }

    @Async
    @TransactionalEventListener
    public void onSubjectDelete(SubjectDeleteEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_DELETE_ROUTING_KEY, EntityMessageType.SUBJECT_DELETE, event.getId());
    }
}
