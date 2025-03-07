package com.pnu.system.file.storage.annotation;

import com.pnu.system.file.storage.config.TimetableFileStorageConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({TimetableFileStorageConfiguration.class})
public @interface EnableTimetableFileStorage {
}
