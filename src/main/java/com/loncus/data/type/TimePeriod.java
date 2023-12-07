/*
  Copyright 2012-2016 Udo Klimaschewski

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package com.loncus.data.type;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import lombok.NonNull;

/**
 * An amount of time expressed in a particular time unit. This class wraps a {@link TemporalUnit}
 * together with a positive integer length, allowing one to create a wide range of different time
 * periods. This class is immutable and thread-safe.
 */
public final class TimePeriod {

  private final TemporalUnit timeUnit;
  private final long length;

  /**
   * Create a new time period with the given unit of time and length.
   *
   * @param timeUnit the unit of time underlying this time period
   * @param length the length of this time period relative to the given unit of time. Note that the
   *     length must be a long. Most decimal time periods can be modeled by converting to an
   *     appropriate time unit with a smaller order of magnitude. For example, the {@link
   *     TimePeriod#halfMonth} constructor works by converting 15.2184375 days to 1314873 seconds.
   * @throws IllegalArgumentException if the given length is less than or equal to 0.
   */
  public TimePeriod(@NonNull TemporalUnit timeUnit, long length) {
    validate(length);
    this.timeUnit = timeUnit;
    this.length = length;
  }

  public static TimePeriod fromAlias(String alias) {
    switch (alias) {
      case "S":
      case "SEC":
        return oneSecond();
      case "MIN":
      case "MINUTE":
        return oneMinutes();
      case "H":
      case "HOUR":
        return oneHour();
      case "D":
      case "DAY":
        return oneDay();
      case "W":
      case "WEEK":
        return oneWeek();
      case "M":
      case "MONTH":
        return oneMonth();
      case "Q":
      case "QUARTER":
        return oneQuarter();
      case "Y":
      case "YEAR":
        return oneYear();
      default:
        throw new IllegalArgumentException("Unknown alias: " + alias);
    }
  }

  /**
   * Create and return a new TimePeriod representing exactly one minutes.
   *
   * @return a new TimePeriod representing exactly one minutes.
   */
  public static TimePeriod oneMinutes() {
    return new TimePeriod(ChronoUnit.MINUTES, 1);
  }

  /**
   * Create and return a new TimePeriod representing exactly one hour.
   *
   * @return a new TimePeriod representing exactly one hour.
   */
  public static TimePeriod oneHour() {
    return new TimePeriod(ChronoUnit.HOURS, 1);
  }

  /**
   * Create and return a new TimePeriod representing exactly one year.
   *
   * @return a new TimePeriod representing exactly one year.
   */
  public static TimePeriod oneYear() {
    return new TimePeriod(ChronoUnit.YEARS, 1);
  }

  /**
   * Create and return a new TimePeriod representing exactly two years.
   *
   * @return a new TimePeriod representing exactly two years.
   */
  public static TimePeriod twoYears() {
    return new TimePeriod(ChronoUnit.YEARS, 2);
  }

  /**
   * Create and return a new TimePeriod representing one half of a decade.
   *
   * @return a new TimePeriod representing one half of a decade.
   */
  public static TimePeriod halfDecade() {
    return new TimePeriod(ChronoUnit.YEARS, 5);
  }

  /**
   * Create and return a new TimePeriod representing exactly one month.
   *
   * @return a new TimePeriod representing exactly one month.
   */
  public static TimePeriod oneMonth() {
    return new TimePeriod(ChronoUnit.MONTHS, 1);
  }

  /**
   * Create and return a new TimePeriod representing one half of a month.
   *
   * @return a new TimePeriod representing one half of a month.
   */
  public static TimePeriod halfMonth() {
    final int secondsInHalfMonth = 1314873;
    return new TimePeriod(ChronoUnit.SECONDS, secondsInHalfMonth);
  }

  /**
   * Create and return a new TimePeriod representing one quarter of a year.
   *
   * @return a new TimePeriod representing one quarter of a year.
   */
  public static TimePeriod oneQuarter() {
    return new TimePeriod(ChronoUnit.MONTHS, 3);
  }

  /**
   * Create and return a new TimePeriod representing one week.
   *
   * @return a new TimePeriod representing one week.
   */
  public static TimePeriod oneWeek() {
    return new TimePeriod(ChronoUnit.WEEKS, 1);
  }

  /**
   * Create and return a new TimePeriod representing one day.
   *
   * @return a new TimePeriod representing one day.
   */
  public static TimePeriod oneDay() {
    return new TimePeriod(ChronoUnit.DAYS, 1);
  }

  /**
   * Create and return a new TimePeriod representing one half of a year.
   *
   * @return a new TimePeriod representing one half of a year.
   */
  public static TimePeriod halfYear() {
    return new TimePeriod(ChronoUnit.MONTHS, 6);
  }

  /**
   * Create and return a new TimePeriod representing exactly one decade.
   *
   * @return a new TimePeriod representing exactly one decade.
   */
  public static TimePeriod oneDecade() {
    return new TimePeriod(ChronoUnit.DECADES, 1);
  }

  /**
   * Create and return a new TimePeriod representing one half of a century.
   *
   * @return a new TimePeriod representing one half of a century.
   */
  public static TimePeriod halfCentury() {
    return new TimePeriod(ChronoUnit.DECADES, 5);
  }

  /**
   * Create and return a new TimePeriod representing exactly one century.
   *
   * @return a new TimePeriod representing exactly one century.
   */
  public static TimePeriod oneCentury() {
    return new TimePeriod(ChronoUnit.CENTURIES, 1);
  }

  /**
   * Create and return a new TimePeriod representing one half of an hour.
   *
   * @return a new TimePeriod representing one half of an hour.
   */
  public static TimePeriod halfHour() {
    return new TimePeriod(ChronoUnit.MINUTES, 30);
  }

  /**
   * Create and return a new TimePeriod representing one half of a day.
   *
   * @return a new TimePeriod representing one half of a day.
   */
  public static TimePeriod halfDay() {
    return new TimePeriod(ChronoUnit.HOURS, 12);
  }

  /**
   * Create and return a new TimePeriod representing one third of a year.
   *
   * @return a new TimePeriod representing one third of a year.
   */
  public static TimePeriod triAnnual() {
    return new TimePeriod(ChronoUnit.MONTHS, 4);
  }

  /**
   * Create and return a new TimePeriod representing one second.
   *
   * @return a new TimePeriod representing one second.
   */
  public static TimePeriod oneSecond() {
    return new TimePeriod(ChronoUnit.SECONDS, 1);
  }

  /**
   * Create and return a new TimePeriod representing one half of a second.
   *
   * @return a new TimePeriod representing one half of a second.
   */
  public static TimePeriod halfSecond() {
    return new TimePeriod(ChronoUnit.MILLIS, 500);
  }

  /**
   * Create and return a new TimePeriod representing one tenth of a second.
   *
   * @return a new TimePeriod representing one tenth of a second.
   */
  public static TimePeriod oneTenthSecond() {
    return new TimePeriod(ChronoUnit.MILLIS, 100);
  }

  /**
   * The unit of time underlying this time period.
   *
   * @return the unit of time underlying this time period.
   */
  TemporalUnit timeUnit() {
    return this.timeUnit;
  }

  /**
   * The length of this time period relative to the underlying time unit.
   *
   * @return the length of this time period relative to the underlying time unit.
   */
  public long length() {
    return this.length;
  }

  /**
   * Compute and return the number of times this time period occurs in the given time period.
   *
   * <p>For example, if this time period is a month and the given time period is half of a year, the
   * return value is 6 since a month occurs 6 times in half of a year.
   *
   * @param otherTimePeriod the time period for which the frequency of occurrence of this time
   *     period is to be found.
   * @return the number of times this time period occurs in the provided time period.
   */
  public double frequencyPer(final TimePeriod otherTimePeriod) {
    return otherTimePeriod.totalSeconds() / this.totalSeconds();
  }

  /**
   * The total amount of time in this time period measured in seconds, the base SI unit of time.
   *
   * @return the total amount of time in this time period measured in seconds.
   */
  public double totalSeconds() {
    final double nanoSecondsPerSecond = 1E9;
    Duration thisDuration = this.timeUnit.getDuration();
    double seconds = thisDuration.getSeconds() * this.length;
    double nanos = thisDuration.getNano();
    nanos = (nanos * this.length);
    nanos = (nanos / nanoSecondsPerSecond);
    return seconds + nanos;
  }

  private void validate(final long periodLength) {
    if (periodLength <= 0) {
      throw new IllegalArgumentException(
          "The given period length must be a positive integer but was " + periodLength);
    }
  }

  @Override
  public String toString() {
    return length
        + " "
        + ((length > 1)
            ? timeUnit
            : timeUnit.toString().substring(0, timeUnit.toString().length() - 1));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimePeriod that = (TimePeriod) o;
    return length == that.length && timeUnit.equals(that.timeUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeUnit, length);
  }
}
