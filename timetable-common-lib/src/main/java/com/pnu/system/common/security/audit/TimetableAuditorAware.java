package com.pnu.system.common.security.audit;

import com.pnu.system.common.utils.UserUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class TimetableAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(UserUtils.getEmail());
    }
}
