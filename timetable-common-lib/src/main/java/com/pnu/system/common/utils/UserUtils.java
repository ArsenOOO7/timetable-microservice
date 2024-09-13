package com.pnu.system.common.utils;

import com.pnu.system.common.secutity.model.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static String getId() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
