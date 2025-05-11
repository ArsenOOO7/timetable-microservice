package com.pnu.system.common.messaging.service;

import com.pnu.system.common.messaging.constant.EntityMessageType;

public interface Publisher {

    void send(String routingKey, Object message);

    void send(String routingKey, EntityMessageType messageType, Object message);

}
