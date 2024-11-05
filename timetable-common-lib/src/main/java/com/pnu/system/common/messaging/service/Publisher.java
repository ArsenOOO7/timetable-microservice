package com.pnu.system.common.messaging.service;

public interface Publisher {

    void send(String routingKey, Object message);

}
