package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;

/** Converter to convert to the BOOLEAN data type. */
public class BooleanConverter implements ConverterIfc {
  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    return EvaluationValue.booleanValue((Boolean) object);
  }

  @Override
  public boolean canConvert(Object object) {
    return object instanceof Boolean;
  }
}
