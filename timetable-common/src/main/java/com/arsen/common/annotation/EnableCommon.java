package com.arsen.common.annotation;

import com.arsen.common.selector.ComponentSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)

@Configuration
@Import(ComponentSelector.class)
public @interface EnableCommon {
    boolean elastic() default false;
}
