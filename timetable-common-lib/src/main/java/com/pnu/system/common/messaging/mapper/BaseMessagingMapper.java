package com.pnu.system.common.messaging.mapper;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import org.mapstruct.Mapping;

public interface BaseMessagingMapper<T> {

    @Mapping(target = "body", source = "body")
    @Mapping(target = "type", source = "type")
    EntityUpdateMessage<T> asEntityUpdateMessage(T body, EntityMessageType type);

    EntityDeleteMessage asEntityDeleteMessage(String id, EntityMessageType type);

}
