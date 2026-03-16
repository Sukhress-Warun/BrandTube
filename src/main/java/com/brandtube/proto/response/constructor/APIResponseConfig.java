package com.brandtube.proto.response.constructor;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Inherited
public @interface APIResponseConfig {
    String name() default "data";
    boolean classNameToSnakeCase() default false;
    boolean isCollection() default false;
}
