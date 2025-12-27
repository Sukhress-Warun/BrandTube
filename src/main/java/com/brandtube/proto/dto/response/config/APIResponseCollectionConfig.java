package com.brandtube.proto.dto.response.config;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Inherited
public @interface APIResponseCollectionConfig {
    String fieldName();
}
