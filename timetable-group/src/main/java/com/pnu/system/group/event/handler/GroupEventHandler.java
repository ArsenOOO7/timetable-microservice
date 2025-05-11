package com.pnu.system.group.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.group.event.model.GroupCreateEvent;
import com.pnu.system.group.event.model.GroupDeleteEvent;
import com.pnu.system.group.event.model.GroupUpdateEvent;
import com.pnu.system.group.service.GroupSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class GroupEventHandler {

    private final Publisher publisher;
    private final GroupSnapshotService service;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void onGroupCreate(GroupCreateEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, EntityMessageType.GROUP_UPDATE, service.getById(event.getGroup().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void onGroupUpdate(GroupUpdateEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, EntityMessageType.GROUP_UPDATE, service.getById(event.getGroup().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void onGroupDelete(GroupDeleteEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_DELETE_ROUTING_KEY, EntityMessageType.GROUP_DELETE, event.getId());
    }
}
