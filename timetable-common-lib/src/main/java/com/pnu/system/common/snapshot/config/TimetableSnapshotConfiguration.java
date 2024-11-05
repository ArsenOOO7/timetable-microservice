package com.pnu.system.common.snapshot.config;

import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.provider.GroupSnapshotProvider;
import com.pnu.system.common.snapshot.provider.LessonLocationSnapshotProvider;
import com.pnu.system.common.snapshot.provider.SubjectSnapshotProvider;
import com.pnu.system.common.snapshot.provider.UserSnapshotProvider;
import com.pnu.system.common.snapshot.provider.impl.RestGroupSnapshotProvider;
import com.pnu.system.common.snapshot.provider.impl.RestLessonLocationSnapshotProvider;
import com.pnu.system.common.snapshot.provider.impl.RestSubjectSnapshotProvider;
import com.pnu.system.common.snapshot.provider.impl.RestUserSnapshotProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimetableSnapshotConfiguration {

    @Bean
    @ConditionalOnMissingBean(GroupSnapshotProvider.class)
    public GroupSnapshotProvider restGroupSnapshotProvider(TimetableRestClient restClient) {
        return new RestGroupSnapshotProvider(restClient);
    }

    @Bean
    @ConditionalOnMissingBean(LessonLocationSnapshotProvider.class)
    public LessonLocationSnapshotProvider restLessonLocationSnapshotProvider(TimetableRestClient restClient) {
        return new RestLessonLocationSnapshotProvider(restClient);
    }

    @Bean
    @ConditionalOnMissingBean(UserSnapshotProvider.class)
    public UserSnapshotProvider restUserSnapshotProvider(TimetableRestClient restClient) {
        return new RestUserSnapshotProvider(restClient);
    }

    @Bean
    @ConditionalOnMissingBean(SubjectSnapshotProvider.class)
    public SubjectSnapshotProvider restSubjectSnapshotProvider(TimetableRestClient restClient) {
        return new RestSubjectSnapshotProvider(restClient);
    }

}
