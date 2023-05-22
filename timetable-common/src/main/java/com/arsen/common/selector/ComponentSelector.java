package com.arsen.common.selector;

import com.arsen.common.annotation.EnableCommon;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;
import java.util.TreeSet;

public class ComponentSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableCommon.class.getName(), false));

        boolean elastic = annotationAttributes.getBoolean("elastic");
        Set<String> basePackages = new TreeSet<>();
        basePackages.add("CommonConfig");

        if(elastic){
            basePackages.add("ElasticConfiguration");
        }

        return basePackages.stream().map(className -> "com.arsen.common.config." + className).toArray(String[]::new);
    }
}
