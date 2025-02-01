package com.pnu.system.common;

import com.pnu.system.common.config.TimetableCommonConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import(TimetableCommonConfiguration.class)
@EnableScheduling
public abstract class AbstractTimetableApplication {


}
