package com.loncus.data.type;

import java.math.BigDecimal;
import java.util.*;
import lombok.NonNull;

/** The type Time series. */
public class TimeSeries {
  private final List<BigDecimal> series;

  private final NavigableSet<Time> times = new TreeSet<>();

  private final Map<Time, Integer> timeToIndexMap;

  private TimeSeries(final List<Time> timeList, final List<BigDecimal> values) {
    this.series = values;

    NavigableSet<Time> times = new TreeSet<>();
    Map<Time, Integer> timeToIndexMap = new HashMap<>(values.size());

    int i = 0;
    for (Time time : timeList) {
      times.add(time);
      timeToIndexMap.put(time, i);
      i++;
    }
    this.times.addAll(times);
    this.timeToIndexMap = Collections.unmodifiableMap(timeToIndexMap);
  }

  public static TimeSeries from(
      @NonNull final List<Date> dates, @NonNull final List<BigDecimal> values) {
    List<Time> times = new ArrayList<>(dates.size());
    for (Date date : dates) {
      times.add(Time.fromYearMonthDay(date.getYear() + 1900, date.getMonth() + 1, date.getDate()));
    }

    return new TimeSeries(times, values);
  }

  public static TimeSeries fromTimes(
      @NonNull final List<Time> times, @NonNull final List<BigDecimal> values) {

    return new TimeSeries(times, values);
  }

  public List<BigDecimal> getValues() {
    return new ArrayList<>(this.series);
  }

  public final NavigableSet<Time> getTimes() {
    return this.times;
  }

  /**
   * get time series values by index
   *
   * @param index the index
   * @return the BigDecimal
   */
  public final BigDecimal at(final int index) {
    if (index < 0 || index >= this.series.size()) {
      throw new IndexOutOfBoundsException("No observation available at index: " + index);
    }
    return this.series.get(index);
  }

  /**
   * get time series values by time
   *
   * @param time the time
   * @param autoComplete the auto complete
   * @return the BigDecimal
   */
  public final BigDecimal at(@NonNull final Time time, Boolean autoComplete) {
    if (!timeToIndexMap.containsKey(time)) {
      if (!autoComplete) {
        throw new IllegalArgumentException("No observation available at time: " + time);
      } else {
        Time prevTime = times.lower(time);
        if (prevTime == null) {
          throw new IllegalArgumentException("No observation available at time: " + time);
        }
        return this.series.get(timeToIndexMap.get(prevTime));
      }
    }

    return this.series.get(timeToIndexMap.get(time));
  }

  /**
   * get time series values by time without auto complete
   *
   * @param time the time
   * @return the BigDecimal
   */
  public final BigDecimal at(@NonNull final Time time) {
    return at(time, false);
  }

  /**
   * This method is used to add a new data point to the TimeSeries object.
   *
   * @param time The time of the new data point.
   * @param value The value of the new data point.
   */
  public void add(Time time, BigDecimal value) {
    if (timeToIndexMap.containsKey(time)) {
      throw new IllegalArgumentException("Time point already exists in the time series.");
    }
    times.add(time);
    series.add(value);
    timeToIndexMap.put(time, series.size() - 1);
  }

  /**
   * This method is used to slice a TimeSeries object. It creates a new TimeSeries object that
   * includes all data points from the start time to the end time.
   *
   * @param start The start time of the slice. This is inclusive.
   * @param end The end time of the slice. This is inclusive.
   * @return A new TimeSeries object that includes all data points from the start time to the end
   *     time.
   */
  public TimeSeries slice(Time start, Time end) {
    NavigableSet<Time> newTimes = times.subSet(start, true, end, true);

    List<BigDecimal> newValues = new ArrayList<>();
    for (Time time : newTimes) {
      int index = timeToIndexMap.get(time);
      newValues.add(series.get(index));
    }

    return new TimeSeries(new ArrayList<>(newTimes), newValues);
  }
}
