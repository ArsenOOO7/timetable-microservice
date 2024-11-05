package com.pnu.system.group.event.handler;

import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.group.event.model.GroupCreateEvent;
import com.pnu.system.group.event.model.GroupDeleteEvent;
import com.pnu.system.group.event.model.GroupUpdateEvent;
import com.pnu.system.group.mapper.GroupMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class GroupEventHandler {

    private final Publisher publisher;
    private final GroupMessageMapper mapper;

    @TransactionalEventListener
    public void onGroupCreate(GroupCreateEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, mapper.asGroupUpdateMessage(event.getGroup()));
    }

    @TransactionalEventListener
    public void onGroupUpdate(GroupUpdateEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY, mapper.asGroupUpdateMessage(event.getGroup()));
    }

    @TransactionalEventListener
    public void onGroupDelete(GroupDeleteEvent event) {
        publisher.send(RabbitRoutingKey.GROUP_DELETE_ROUTING_KEY, mapper.asGroupDeleteMessage(event.getId()));
    }
}
