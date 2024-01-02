package com.loncus.data.type;

import java.math.BigDecimal;

/**
 * This class represents a point in a time series.
 * A time series point consists of a time and a value from a time series at that time.
 */
public class TimeSeriesPoint {
  private TimeSeries timeSeries;

  private Time time;

  /**
   * Constructs a new TimeSeriesPoint with the specified time series and time.
   *
   * @param timeSeries the time series to which this point belongs
   * @param time the time at which this point exists
   */
  public TimeSeriesPoint(TimeSeries timeSeries, Time time) {
    this.timeSeries = timeSeries;
    this.time = time;
  }

  /**
   * Returns the time series to which this point belongs.
   *
   * @return the time series to which this point belongs
   */
  public TimeSeries getTimeSeries() {
    return timeSeries;
  }

  /**
   * Returns the time at which this point exists.
   *
   * @return the time at which this point exists
   */
  public Time getTime() {
    return time;
  }

  /**
   * Returns the value of this point from the time series at its time.
   *
   * @return the value of this point from the time series at its time
   */
  public BigDecimal getValue() {
    return timeSeries.at(time, true);
  }

  /**
   * Sets the time at which this point exists and returns this point.
   *
   * @param time the new time at which this point should exist
   * @return this point
   */
  public TimeSeriesPoint setTime(Time time) {
    this.time = time;
    return this;
  }
}