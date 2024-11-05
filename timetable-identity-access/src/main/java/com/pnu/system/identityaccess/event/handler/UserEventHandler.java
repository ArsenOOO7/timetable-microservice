package com.pnu.system.identityaccess.event.handler;

import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.identityaccess.event.model.TeacherProfileUpdateEvent;
import com.pnu.system.identityaccess.event.model.UserCreateEvent;
import com.pnu.system.identityaccess.event.model.UserDeleteEvent;
import com.pnu.system.identityaccess.event.model.UserUpdateEvent;
import com.pnu.system.identityaccess.mapper.UserMessageMapper;
import com.pnu.system.identityaccess.repository.UserWithTeacherProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final Publisher publisher;
    private final UserMessageMapper mapper;
    private final UserWithTeacherProfileRepository repository;

    @TransactionalEventListener
    public void onUserCreate(UserCreateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, mapper.asUserUpdateMessage(repository.getReferenceById(event.getUser().getId())));
    }

    @TransactionalEventListener
    public void onUserUpdate(UserUpdateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, mapper.asUserUpdateMessage(repository.getReferenceById(event.getUser().getId())));
    }

    @TransactionalEventListener
    public void onUserUpdate(TeacherProfileUpdateEvent event) {
        publisher.send(RabbitRoutingKey.USER_UPDATE_ROUTING_KEY, mapper.asUserUpdateMessage(repository.getReferenceById(event.getProfile().getId())));
    }

    @TransactionalEventListener
    public void onUserDelete(UserDeleteEvent event) {
        publisher.send(RabbitRoutingKey.USER_DELETE_ROUTING_KEY, mapper.asUserDeleteMessage(event.getId()));
    }
}
