package com.joco.spring.config;

import java.time.Clock;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.joco.TimeUtilsExtension;

/**
 * SpringBootTestBase
 */
@ExtendWith(TimeUtilsExtension.class)
@SpringBootTest
@ContextConfiguration(classes = { DummySpringApplication.class }, initializers = SpringBootITBase.Initializer.class)
public class SpringBootITBase {

  private static Supplier<Clock> clockSupplier;

  @BeforeAll
  public static void beforeAll(Supplier<Clock> clockSupplier) {
    SpringBootITBase.clockSupplier = clockSupplier;
  }

  static class Initializer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
      applicationContext.registerBean(Clock.class, clockSupplier, (BeanDefinition bd) -> bd.setPrimary(true));
    }

  }
}
