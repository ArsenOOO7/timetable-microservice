package com.pnu.system.group.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.group.event.model.GroupCreateEvent;
import com.pnu.system.group.event.model.GroupDeleteEvent;
import com.pnu.system.group.event.model.GroupUpdateEvent;
import com.pnu.system.group.mapper.GroupMessagingMapper;
import com.pnu.system.group.service.GroupSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class GroupEventHandler {

    private final Publisher publisher;
    private final GroupMessagingMapper mapper;
    private final GroupSnapshotService service;

    @TransactionalEventListener
    public void onGroupCreate(GroupCreateEvent event) {
        EntityUpdateMessage<GroupSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getGroup().getId()), EntityMessageType.GROUP);
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onGroupUpdate(GroupUpdateEvent event) {
        EntityUpdateMessage<GroupSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getGroup().getId()), EntityMessageType.GROUP);
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onGroupDelete(GroupDeleteEvent event) {
        EntityDeleteMessage message = mapper.asEntityDeleteMessage(event.getId(), EntityMessageType.GROUP);
        publisher.send(RabbitRoutingKey.GROUP_DELETE_ROUTING_KEY, message);
    }
}
