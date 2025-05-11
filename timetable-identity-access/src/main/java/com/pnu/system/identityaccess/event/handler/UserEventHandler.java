package com.pnu.system.identityaccess.event.handler;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.identityaccess.event.model.TeacherProfileUpdateEvent;
import com.pnu.system.identityaccess.event.model.UserCreateEvent;
import com.pnu.system.identityaccess.event.model.UserDeleteEvent;
import com.pnu.system.identityaccess.event.model.UserUpdateEvent;
import com.pnu.system.identityaccess.service.UserSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final Publisher publisher;
    private final UserSnapshotService service;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onUserCreate(UserCreateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, EntityMessageType.USER_UPDATE, service.getById(event.getUser().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onUserUpdate(UserUpdateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, EntityMessageType.USER_UPDATE, service.getById(event.getUser().getId()));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @TransactionalEventListener
    public void onUserUpdate(TeacherProfileUpdateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, EntityMessageType.USER_UPDATE, service.getById(event.getProfile().getId()));
    }

    @Async
    @TransactionalEventListener
    public void onUserDelete(UserDeleteEvent event) {
        publisher.send(RabbitRoutingKey.USER_DELETE_ROUTING_KEY, EntityMessageType.USER_DELETE, event.getId());
    }
}
