package com.joco;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimeUtilsExtension.class)
@CurrentTime(datePattern = "2022-11-05", timePattern = "00:00", zoneId = "UTC")
public class CurrentTimeUTCTest {

  @Test
  @DisplayName("Class level annotation should set time correctly for LocalDateTime")
  public void testClassAnnotationShouldSetLocalDateTimeCorrectly(Supplier<Clock> clockSupplier) {
    var clock = clockSupplier.get();
    var actual = LocalDateTime.now(clock);
    var expected = LocalDate.of(2022, 11, 5).atTime(LocalTime.of(0, 0));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Class level annotation should set time correctly for ZonedDateTime")
  public void testClassAnnotationShouldSetZonedDateTimeCorrectly(Supplier<Clock> clockSupplier) {
    var clock = clockSupplier.get();
    var actual = ZonedDateTime.now(clock);
    var expected = ZonedDateTime.parse("2022-11-05T00:00Z[UTC]");
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @CurrentTime(datePattern = "2021-01-02", timePattern = "01:01", zoneId = "UTC")
  @DisplayName("Method level annotation should set time correctly for LocalDateTime")
  public void testMethodAnnotationShouldSetLocalDateTimeCorrectly(Supplier<Clock> clockSupplier) {
    var clock = clockSupplier.get();
    var actual = LocalDateTime.now(clock);
    var expected = LocalDate.of(2021, 1, 2)
        .atTime(LocalTime.of(1, 1));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @CurrentTime(datePattern = "2021-01-02", timePattern = "01:01", zoneId = "UTC")
  @DisplayName("Method level annotation should set time correctly for ZonedDateTime")
  public void testMethodAnnotationShouldSetZonedDateTimeCorrectly(Supplier<Clock> clockSupplier) {
    var clock = clockSupplier.get();
    var actual = ZonedDateTime.now(clock);
    var expected = ZonedDateTime.parse("2021-01-02T01:01Z[UTC]");
    Assertions.assertEquals(expected, actual);
  }

  public static class DummyClass {
    private final Clock clock;

    public DummyClass(Clock clock) {
      this.clock = clock;
    }

    public String currentTime() {
      return LocalDateTime.now(clock).toString();
    }
  }
}
