package com.joco;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentTime {
  static final String DEFAULT_ZONE_ID = "UTC";

  String timePattern();

  String datePattern();

  String zoneId() default DEFAULT_ZONE_ID;
}
