package com.joco.spring.config;

import java.time.Clock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joco.DummyTimeUserClass;

/**
 * DummySpringApplication
 */
@SpringBootApplication
public class DummySpringApplication {

  @Configuration
  public static class SpringContext {

    @Bean
    public Clock clockBean() {
      return Clock.systemUTC();
    }

    @Bean
    public DummyTimeUserClass dummyTimeUserClass(Clock clock) {
      return new DummyTimeUserClass(clock);
    }
  }

}
