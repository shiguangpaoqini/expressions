package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import java.time.Duration;

/** Converter to convert to the DURATION data type. */
public class DurationConverter implements ConverterIfc {
  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    return EvaluationValue.durationValue((Duration) object);
  }

  @Override
  public boolean canConvert(Object object) {
    return object instanceof Duration;
  }
}
