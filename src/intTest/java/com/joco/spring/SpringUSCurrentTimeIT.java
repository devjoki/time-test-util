package com.joco.spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joco.CurrentTime;
import com.joco.DummyTimeUserClass;
import com.joco.spring.config.SpringBootITBase;

/**
 * SpringBootIntegrationTest
 */
@CurrentTime(datePattern = "2022-11-05", timePattern = "00:00", zoneId = "America/New_York")
public class SpringUSCurrentTimeIT extends SpringBootITBase {
  @Autowired
  private DummyTimeUserClass dummyTimeUserClass;

  @Test
  @Order(1)
  @DisplayName("Class level annotation with US timezone should set the time properly")
  public void testClassLevelAnnotationForUS() {
    // GIVEN
    var zoneId = ZoneId.of("America/New_York");
    var expectedLocalDateTime = getLocalTimeAtTimeZone(LocalDate.of(2022, 11, 5).atTime(LocalTime.of(0, 0)), zoneId);
    var expectedZonedDateTime = ZonedDateTime.parse("2022-11-05T00:00Z[UTC]");
    var expectedOffsetDateTime = OffsetDateTime.parse("2022-11-05T00:00Z");
    // WHEN
    var actualLocalDateTime = dummyTimeUserClass.currentLocalTime();
    var actualZonedDateTime = dummyTimeUserClass.currentZonedTime();
    var actualOffsetDateTime = dummyTimeUserClass.currentOffsetTime();
    // THEN
    Assertions.assertThat(actualLocalDateTime).isEqualTo(expectedLocalDateTime);
    Assertions.assertThat(actualZonedDateTime).isEqualTo(expectedZonedDateTime);
    Assertions.assertThat(actualOffsetDateTime).isEqualTo(expectedOffsetDateTime);
  }

  @Test
  @Order(2)
  @DisplayName("Method level annotation with US timezone should set the time properly")
  @CurrentTime(datePattern = "2021-12-15", timePattern = "05:30", zoneId = "America/Sao_Paulo")
  public void testMethodLevelAnnotationForUS() {
    // GIVEN
    var zoneId = ZoneId.of("America/Sao_Paulo");
    var expectedLocalDateTime = getLocalTimeAtTimeZone(LocalDate.of(2021, 12, 15).atTime(LocalTime.of(5, 30)), zoneId);
    var expectedZonedDateTime = ZonedDateTime.parse("2021-12-15T05:30Z[UTC]");
    var expectedOffsetDateTime = OffsetDateTime.parse("2021-12-15T05:30Z");
    // WHEN
    var actualLocalDateTime = dummyTimeUserClass.currentLocalTime();
    var actualZonedDateTime = dummyTimeUserClass.currentZonedTime();
    var actualOffsetDateTime = dummyTimeUserClass.currentOffsetTime();
    // THEN
    Assertions.assertThat(actualLocalDateTime).isEqualTo(expectedLocalDateTime);
    Assertions.assertThat(actualZonedDateTime).isEqualTo(expectedZonedDateTime);
    Assertions.assertThat(actualOffsetDateTime).isEqualTo(expectedOffsetDateTime);
  }

  @Test
  @Order(3)
  @DisplayName("Class level annotation with US timezone should set the time properly after method level annotation was used on another test")
  public void testClassLevelAnnotationForUSAfterMethodLevelAnnotationWasUsed() {
    // GIVEN
    var zoneId = ZoneId.of("America/New_York");
    var expectedLocalDateTime = getLocalTimeAtTimeZone(LocalDate.of(2022, 11, 5).atTime(LocalTime.of(0, 0)), zoneId);
    var expectedZonedDateTime = ZonedDateTime.parse("2022-11-05T00:00Z[UTC]");
    var expectedOffsetDateTime = OffsetDateTime.parse("2022-11-05T00:00Z");
    // WHEN
    var actualLocalDateTime = dummyTimeUserClass.currentLocalTime();
    var actualZonedDateTime = dummyTimeUserClass.currentZonedTime();
    var actualOffsetDateTime = dummyTimeUserClass.currentOffsetTime();
    // THEN
    Assertions.assertThat(actualLocalDateTime).isEqualTo(expectedLocalDateTime);
    Assertions.assertThat(actualZonedDateTime).isEqualTo(expectedZonedDateTime);
    Assertions.assertThat(actualOffsetDateTime).isEqualTo(expectedOffsetDateTime);
  }

  private LocalDateTime getLocalTimeAtTimeZone(LocalDateTime dateTime, ZoneId zoneId) {
    return ZonedDateTime.of(dateTime, ZoneOffset.UTC)
        .withZoneSameInstant(zoneId)
        .toLocalDateTime();
  }

}
