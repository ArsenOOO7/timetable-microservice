package com.pnu.system.common.messaging.mapper;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import org.mapstruct.Mapping;

import java.util.List;

public interface BaseMessagingMapper<T> {

    @Mapping(target = "body", source = "body")
    @Mapping(target = "type", source = "type")
    EntityUpdateMessage<T> asEntityUpdateMessage(T body, EntityMessageType type);

    default List<EntityUpdateMessage<T>> asEntityUpdateMessages(List<T> bodies, EntityMessageType type) {
        return bodies.stream().map(body -> asEntityUpdateMessage(body, type)).toList();
    }

    EntityDeleteMessage asEntityDeleteMessage(String id, EntityMessageType type);

}
