package com.pnu.system.common.messaging.configuration;

import com.pnu.system.common.messaging.service.Publisher;
import com.pnu.system.common.messaging.service.impl.SnsPublisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pnu.system.common.constant.TimetableProfiles.PRODUCTION;

@Configuration
@Profile(PRODUCTION)
public class SNSConfiguration {

    @Bean
    @ConditionalOnMissingBean(Publisher.class)
    public Publisher rabbitPublisher() {
        return new SnsPublisher();
    }

}
