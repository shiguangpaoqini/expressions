package com.loncus.data.type;

import java.math.BigDecimal;
import java.util.*;
import lombok.NonNull;

/**
 * This class represents a time series, a sequence of numerical data points in successive order.
 */
public class TimeSeries {
  // A list of numerical data points
  private final List<BigDecimal> series;

  private final NavigableSet<Time> times = new TreeSet<>();

  private final Map<Time, Integer> timeToIndexMap;

  /**
   * Private constructor for creating a TimeSeries object.
   *
   * @param timeList a list of time points
   * @param values a list of numerical data points
   */
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

  /**
   * Factory method for creating a TimeSeries object from a list of dates and values.
   *
   * @param dates a list of dates
   * @param values a list of numerical data points
   * @return a new TimeSeries object
   */
  public static TimeSeries from(
      @NonNull final List<Date> dates, @NonNull final List<BigDecimal> values) {
    List<Time> times = new ArrayList<>(dates.size());
    for (Date date : dates) {
      times.add(Time.fromYearMonthDay(date.getYear() + 1900, date.getMonth() + 1, date.getDate()));
    }

    return new TimeSeries(times, values);
  }

  /**
   * Factory method for creating a TimeSeries object from a list of Time objects and values.
   *
   * @param times a list of Time objects
   * @param values a list of numerical data points
   * @return a new TimeSeries object
   */
  public static TimeSeries fromTimes(
      @NonNull final List<Time> times, @NonNull final List<BigDecimal> values) {

    return new TimeSeries(times, values);
  }

  /**
   * Returns a copy of the data points in the time series.
   *
   * @return a list of numerical data points
   */
  public List<BigDecimal> getValues() {
    return new ArrayList<>(this.series);
  }

  /**
   * Returns the time points in the time series.
   *
   * @return a set of time points
   */
  public final NavigableSet<Time> getTimes() {
    return this.times;
  }

  /**
   * Returns the data point at the specified index.
   *
   * @param index the index of the data point
   * @return the data point at the specified index
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public final BigDecimal at(final int index) {
    if (index < 0 || index >= this.series.size()) {
      throw new IndexOutOfBoundsException("No observation available at index: " + index);
    }
    return this.series.get(index);
  }

  /**
   * Returns the data point at the specified time.
   *
   * @param time the time of the data point
   * @param autoComplete if true, returns the data point at the previous time if the specified time does not exist
   * @return the data point at the specified time
   * @throws IllegalArgumentException if the time does not exist in the time series
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
   * Returns the data point at the specified time.
   *
   * @param time the time of the data point
   * @return the data point at the specified time
   * @throws IllegalArgumentException if the time does not exist in the time series
   */
  public final BigDecimal at(@NonNull final Time time) {
    return at(time, false);
  }

  /**
   * Adds a new data point to the time series.
   *
   * @param time the time of the new data point
   * @param value the new data point
   * @throws IllegalArgumentException if the time already exists in the time series
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
   * Returns a new TimeSeries object that is a slice of this time series between the specified start and end times.
   *
   * @param start the start time of the slice
   * @param end the end time of the slice
   * @return a new TimeSeries object that is a slice of this time series
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