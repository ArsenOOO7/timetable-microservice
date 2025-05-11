package com.pnu.system.common.messaging.service.impl;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.service.Publisher;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

@RequiredArgsConstructor
public class SnsPublisher implements Publisher {

    @Override
    public void send(String routingKey, Object message) {
        throw new NotImplementedException("SNS is not implemented yet.");
    }

    @Override
    public void send(String routingKey, EntityMessageType messageType, Object message) {
        throw new NotImplementedException("SNS is not implemented yet.");
    }
}
