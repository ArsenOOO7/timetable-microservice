package com.pnu.system.timetable.messaging;

import com.pnu.system.common.messaging.constant.RabbitRoutingKey;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.timetable.service.TimetableGroupSnapshotService;
import com.pnu.system.timetable.service.TimetableLessonLocationSnapshotService;
import com.pnu.system.timetable.service.TimetableSubjectSnapshotService;
import com.pnu.system.timetable.service.TimetableUserSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.pnu.system.common.constant.TimetableProfiles.LOCAL;

@Component
@Profile(LOCAL)
@RequiredArgsConstructor
public class TimetableRabbitMessagingListener {

    private final TimetableUserSnapshotService userSnapshotService;
    private final TimetableGroupSnapshotService groupSnapshotService;
    private final TimetableSubjectSnapshotService subjectSnapshotService;
    private final TimetableLessonLocationSnapshotService lessonLocationSnapshotService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.user.update}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.USER_UPDATE_ROUTING_KEY
    ))
    public void receiveUserUpdate(@Payload EntityUpdateMessage<UserSnapshotDto> userUpdateMessage) {
        userSnapshotService.receiveSnapshot(userUpdateMessage.getBody());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.user.delete}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.USER_DELETE_ROUTING_KEY
    ))
    public void markUserAsDeleted(@Payload EntityDeleteMessage userDeleteMessage) {
        userSnapshotService.markAsDeleted(userDeleteMessage.getId());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.subject.update}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.SUBJECT_UPDATE_ROUTING_KEY
    ))
    public void receiveSubjectUpdate(@Payload EntityUpdateMessage<SubjectSnapshotDto> subjectUpdateMessage) {
        subjectSnapshotService.receiveSnapshot(subjectUpdateMessage.getBody());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.subject.delete}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.SUBJECT_DELETE_ROUTING_KEY
    ))
    public void markSubjectAsDeleted(@Payload EntityDeleteMessage userDeleteMessage) {
        subjectSnapshotService.markAsDeleted(userDeleteMessage.getId());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.group.update}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.GROUP_UPDATE_ROUTING_KEY
    ))
    public void receiveGroupUpdate(@Payload EntityUpdateMessage<GroupSnapshotDto> groupUpdateMessage) {
        groupSnapshotService.receiveSnapshot(groupUpdateMessage.getBody());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.group.delete}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.GROUP_DELETE_ROUTING_KEY
    ))
    public void markGroupAsDeleted(@Payload EntityDeleteMessage userDeleteMessage) {
        groupSnapshotService.markAsDeleted(userDeleteMessage.getId());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.location.update}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.LESSON_LOCATION_UPDATE_ROUTING_KEY
    ))
    public void receiveLocationUpdate(@Payload EntityUpdateMessage<LessonLocationSnapshotDto> locationUpdateMessage) {
        lessonLocationSnapshotService.receiveSnapshot(locationUpdateMessage.getBody());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${messaging.rabbit.queue.location.delete}", durable = "true"),
            exchange = @Exchange(value = "${messaging.rabbit.topic}", type = ExchangeTypes.TOPIC),
            key = RabbitRoutingKey.LESSON_LOCATION_DELETE_ROUTING_KEY
    ))
    public void markLocationAsDeleted(@Payload EntityDeleteMessage userDeleteMessage) {
        lessonLocationSnapshotService.markAsDeleted(userDeleteMessage.getId());
    }
}
