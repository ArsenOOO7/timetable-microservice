package com.pnu.system.common.messaging.model;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityUpdateMessage<T> {

    private EntityMessageType type;
    private T body;

}
