package com.pnu.system.common.messaging.service;

import com.pnu.system.common.messaging.constant.EntityMessageType;

public interface MessagingSnapshotService<T> {

    void receiveSnapshot(T snapshot);

    void markAsDeleted(String id);

    boolean supports(EntityMessageType type);

}
