package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import com.loncus.data.type.TimeSeriesPoint;

/** Converter to convert to the TIME_SERIES_POINT data type. */
public class TimeSeriesPointConverter implements ConverterIfc {
  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    return EvaluationValue.timeSeriesPointValue((TimeSeriesPoint) object);
  }

  @Override
  public boolean canConvert(Object object) {
    return object instanceof TimeSeriesPoint;
  }
}
