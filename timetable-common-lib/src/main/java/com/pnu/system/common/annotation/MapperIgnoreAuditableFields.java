package com.pnu.system.common.annotation;


import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "createdAt", ignore = true)
@Mapping(target = "createdBy", ignore = true)
@Mapping(target = "lastModifiedAt", ignore = true)
@Mapping(target = "lastModifiedBy", ignore = true)
@Mapping(target = "version", ignore = true)
public @interface MapperIgnoreAuditableFields {
}
