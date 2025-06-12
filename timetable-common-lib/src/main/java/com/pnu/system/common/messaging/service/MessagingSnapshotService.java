package com.pnu.system.common.messaging.service;

public interface MessagingSnapshotService<T> {

    void synchronize();

    void receiveSnapshot(T snapshot);

    void markAsDeleted(String id);

}
