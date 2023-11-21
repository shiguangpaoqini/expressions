package com.loncus.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeTest {

  @Test
  void isAfter() {
    Time time = Time.fromYearMonthDay(2023, 1, 1);
    Time time1 = Time.fromYearMonthDay(2021, 1, 1);
    assertTrue(time.isAfter(time1));
  }

  @Test
  void isBefore() {
    Time time = Time.fromYearMonthDay(2023, 11, 21);
    Time time1 = Time.fromYearMonthDay(2023, 11, 22);
    assertTrue(time.isBefore(time1));
  }
}
