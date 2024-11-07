package com.pnu.system.identityaccess.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.identityaccess.event.model.TeacherProfileUpdateEvent;
import com.pnu.system.identityaccess.event.model.UserCreateEvent;
import com.pnu.system.identityaccess.event.model.UserDeleteEvent;
import com.pnu.system.identityaccess.event.model.UserUpdateEvent;
import com.pnu.system.identityaccess.mapper.UserMessagingMapper;
import com.pnu.system.identityaccess.service.UserSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final Publisher publisher;
    private final UserMessagingMapper mapper;
    private final UserSnapshotService service;

    @TransactionalEventListener
    public void onUserCreate(UserCreateEvent event) {
        EntityUpdateMessage<UserSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getUser().getId()), EntityMessageType.USER);
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onUserUpdate(UserUpdateEvent event) {
        EntityUpdateMessage<UserSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getUser().getId()), EntityMessageType.USER);
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onUserUpdate(TeacherProfileUpdateEvent event) {
        EntityUpdateMessage<UserSnapshotDto> message = mapper.asEntityUpdateMessage(service.getById(event.getProfile().getId()), EntityMessageType.USER);
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, message);
    }

    @TransactionalEventListener
    public void onUserDelete(UserDeleteEvent event) {
        EntityDeleteMessage message = mapper.asEntityDeleteMessage(event.getId(), EntityMessageType.USER);
        publisher.send(RabbitRoutingKey.USER_DELETE_ROUTING_KEY, message);
    }
}
