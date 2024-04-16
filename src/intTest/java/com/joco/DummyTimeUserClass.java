package com.joco;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DummyTimeUserClass
 */
@Getter
@AllArgsConstructor
public class DummyTimeUserClass {

  private final Clock clock;

// public DummyTimeUserClass(Clock clock) {
//   this.clock = clock;
// }

  public LocalDateTime currentLocalTime() {
    getClock();
    return LocalDateTime.now(clock);
  }

  public ZonedDateTime currentZonedTime() {
    return ZonedDateTime.now(clock);
  }

  public OffsetDateTime currentOffsetTime() {
    return OffsetDateTime.now(clock);
  }

}
