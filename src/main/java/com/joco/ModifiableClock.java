package com.joco;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

public class ModifiableClock extends Clock {
  private Optional<Clock> classLevelClock = Optional.empty();
  private Optional<Clock> methodLevelClock = Optional.empty();
  private final Clock fallback;

  public ModifiableClock() {
    this.fallback = Clock.systemUTC();
  }

  private ModifiableClock(Optional<Clock> classLevelClock, Optional<Clock> methodLevelClock, Clock fallback) {
    this.classLevelClock = classLevelClock;
    this.methodLevelClock = methodLevelClock;
    this.fallback = fallback;
  }

  ModifiableClock newMethodLevelTime(LocalDateTime newDateTime, ZoneId newZoneId) {
    methodLevelClock = Optional.of(Clock.fixed(newDateTime.toInstant(ZoneOffset.UTC), newZoneId));
    return this;
  }

  ModifiableClock newClassLevelTime(LocalDateTime newDateTime, ZoneId newZoneId) {
    classLevelClock = Optional.of(Clock.fixed(newDateTime.toInstant(ZoneOffset.UTC), newZoneId));
    return this;
  }

  ModifiableClock clearMethodLevelClock() {
    this.methodLevelClock = Optional.empty();
    return this;
  }

  ModifiableClock clearClassLevelClock() {
    this.classLevelClock = Optional.empty();
    return this;
  }

  @Override
  public ZoneId getZone() {
    return getClockToUse().getZone();
  }

  @Override
  public Clock withZone(ZoneId zone) {
    return new ModifiableClock(
        classLevelClock.map(it -> it.withZone(zone)),
        methodLevelClock.map(it -> it.withZone(zone)),
        fallback.withZone(zone));
  }

  @Override
  public Instant instant() {
    return getClockToUse().instant();
  }

  private Clock getClockToUse() {
    return methodLevelClock
        .or(() -> classLevelClock)
        .orElse(fallback);
  }

}
