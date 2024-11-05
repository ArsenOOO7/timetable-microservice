package com.pnu.system.common.messaging.model;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDeleteMessage {

    private EntityMessageType type;
    private String id;

}
