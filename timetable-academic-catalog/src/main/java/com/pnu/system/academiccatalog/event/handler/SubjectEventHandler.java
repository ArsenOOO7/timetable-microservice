package com.pnu.system.academiccatalog.event.handler;

import com.pnu.system.academiccatalog.event.model.SubjectCreateEvent;
import com.pnu.system.academiccatalog.event.model.SubjectDeleteEvent;
import com.pnu.system.academiccatalog.event.model.SubjectUpdateEvent;
import com.pnu.system.academiccatalog.mapper.SubjectMessagingMapper;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SubjectEventHandler {

    private final Publisher publisher;
    private final SubjectMessagingMapper mapper;

    @TransactionalEventListener
    public void onSubjectCreate(SubjectCreateEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, mapper.asSubjectUpdateMessage(event.getSubject()));
    }

    @TransactionalEventListener
    public void onSubjectUpdate(SubjectUpdateEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY, mapper.asSubjectUpdateMessage(event.getSubject()));
    }

    @TransactionalEventListener
    public void onSubjectDelete(SubjectDeleteEvent event) {
        publisher.send(RabbitRoutingKey.SUBJECT_DELETE_ROUTING_KEY, mapper.asSubjectDeleteMessage(event.getId()));
    }
}
