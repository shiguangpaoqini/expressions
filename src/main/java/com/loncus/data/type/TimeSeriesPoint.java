package com.loncus.data.type;

import java.math.BigDecimal;

public class TimeSeriesPoint {
  private TimeSeries timeSeries;
  private Time time;

  public TimeSeriesPoint(TimeSeries timeSeries, Time time) {
    this.timeSeries = timeSeries;
    this.time = time;
  }

  public TimeSeries getTimeSeries() {
    return timeSeries;
  }

  public Time getTime() {
    return time;
  }

  public BigDecimal getValue() {
    return timeSeries.at(time);
  }

  public TimeSeriesPoint setTime(Time time) {
    this.time = time;
    return this;
  }
}
