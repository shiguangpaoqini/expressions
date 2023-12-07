package com.loncus.data.type;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import lombok.NonNull;

/** Represents a particular point in time. */
public final class Time implements Comparable<Time> {

  private final OffsetDateTime dateTime;

  Time(@NonNull OffsetDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Time(Builder builder) {
    this.dateTime =
        OffsetDateTime.of(
            builder.year,
            builder.month,
            builder.day,
            builder.hour,
            builder.minute,
            builder.second,
            builder.nanos,
            builder.zoneOffset);
  }

  static Time now() {
    return new Time(OffsetDateTime.now());
  }

  public static Time fromYear(int year) {
    ZoneOffset utc = ZoneOffset.UTC;
    OffsetDateTime dateTime = OffsetDateTime.of(year, 1, 1, 0, 0, 0, 0, utc);
    return new Time(dateTime);
  }

  public static Time fromYearMonth(int year, int month) {
    ZoneOffset utc = ZoneOffset.UTC;
    OffsetDateTime dateTime = OffsetDateTime.of(year, month, 1, 0, 0, 0, 0, utc);
    return new Time(dateTime);
  }

  public static Time fromYearMonthDay(int year, int month, int day) {
    ZoneOffset utc = ZoneOffset.UTC;
    OffsetDateTime dateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, utc);
    return new Time(dateTime);
  }

  public Time plus(TimePeriod timePeriod) {
    return plus(timePeriod.length(), timePeriod);
  }

  public Time plus(long amountToAdd, TimePeriod timePeriod) {
    OffsetDateTime addedDateTime = this.dateTime.plus(amountToAdd, timePeriod.timeUnit());
    return new Time(addedDateTime);
  }

  public Time minus(TimePeriod timePeriod) {
    return minus(timePeriod.length(), timePeriod);
  }

  public Time minus(long amountToSubtract, TimePeriod timePeriod) {
    OffsetDateTime subtractedDateTime =
        this.dateTime.minus(amountToSubtract, timePeriod.timeUnit());
    return new Time(subtractedDateTime);
  }

  public Instant toInstant() {
    return this.dateTime.toInstant();
  }

  @Override
  public int compareTo(@NonNull Time otherTime) {
    return this.dateTime.compareTo(otherTime.dateTime);
  }

  public boolean isAfter(Time otherTime) {
    return this.dateTime.isAfter(otherTime.dateTime);
  }

  public boolean isBefore(Time otherTime) {
    return this.dateTime.isBefore(otherTime.dateTime);
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Time time = (Time) o;
    return dateTime.equals(time.dateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateTime);
  }

  @Override
  public String toString() {
    return this.dateTime.toString();
  }

  public LocalDateTime toLocalDateTime() {
    return this.dateTime.toLocalDateTime();
  }

  public String prettyPrint() {
    StringBuilder patternBuilder = new StringBuilder();
    patternBuilder.append("MMM dd uuuu");
    if (!timeEmpty(dateTime)) {
      patternBuilder.append(" 'at' ");
      addHour(patternBuilder);
      addMinute(dateTime, patternBuilder);
      addSecond(dateTime, patternBuilder);
      addTimeOfDay(patternBuilder);
    }
    if (!nanoEmpty(dateTime)) {
      addNanos(patternBuilder);
    }
    addOffset(patternBuilder);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternBuilder.toString());
    return dateTime.format(formatter);
  }

  private boolean timeEmpty(OffsetDateTime dateTime) {
    return dateTime.getHour() == 0
        && dateTime.getMinute() == 0
        && dateTime.getSecond() == 0
        && dateTime.getNano() == 0;
  }

  private void addHour(StringBuilder builder) {
    builder.append("hh");
  }

  private void addMinute(OffsetDateTime dateTime, StringBuilder builder) {
    if (!(dateTime.getMinute() == 0 && dateTime.getSecond() == 0)) {
      builder.append(":mm");
    }
  }

  private void addSecond(OffsetDateTime dateTime, StringBuilder builder) {
    if (dateTime.getSecond() != 0) {
      builder.append(":ss");
    }
  }

  private void addTimeOfDay(StringBuilder builder) {
    builder.append(" a");
  }

  private void addNanos(StringBuilder builder) {
    builder.append(" 'plus' n 'nanoseconds'");
  }

  private void addOffset(StringBuilder builder) {
    builder.append(" 'with offset' O");
  }

  private boolean nanoEmpty(OffsetDateTime dateTime) {
    return dateTime.getNano() == 0;
  }

  public static class Builder {

    private int year = 1;
    private int month = 1;
    private int day = 1;

    private int hour;
    private int minute;
    private int second;
    private int nanos;

    private ZoneOffset zoneOffset = ZoneOffset.UTC;

    public Builder setYear(int year) {
      this.year = year;
      return this;
    }

    public Builder setMonth(int month) {
      this.month = month;
      return this;
    }

    public Builder setDay(int day) {
      this.day = day;
      return this;
    }

    public Builder setHour(int hour) {
      this.hour = hour;
      return this;
    }

    public Builder setMinute(int minute) {
      this.minute = minute;
      return this;
    }

    public Builder setSecond(int second) {
      this.second = second;
      return this;
    }

    public Builder setNanos(int nanos) {
      this.nanos = nanos;
      return this;
    }

    public Builder setOffset(ZoneOffset zoneOffset) {
      this.zoneOffset = zoneOffset;
      return this;
    }

    public Builder setDate(Date date) {
      Instant instant = date.toInstant();
      OffsetDateTime dateTime = instant.atOffset(zoneOffset);
      this.year = dateTime.getYear();
      this.month = dateTime.getMonthValue();
      this.day = dateTime.getDayOfMonth();
      this.hour = dateTime.getHour();
      this.minute = dateTime.getMinute();
      this.second = dateTime.getSecond();
      this.nanos = dateTime.getNano();
      return this;
    }

    public Time build() {
      return new Time(this);
    }
  }
}
