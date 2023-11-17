package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;

/** Converter interface used by the {@link DefaultEvaluationValueConverter}. */
public interface ConverterIfc {

  /**
   * Called to convert a previously checked data type.
   *
   * @param object The object to convert.
   * @param configuration The current expression configuration.
   * @return The converted value.
   */
  EvaluationValue convert(Object object, ExpressionConfiguration configuration);

  /**
   * Checks, if a given object can be converted by this converter.
   *
   * @param object The object to convert.
   * @return <code>true</code> if the object can be converted, false otherwise.
   */
  boolean canConvert(Object object);

  default IllegalArgumentException illegalArgument(Object object) {
    return new IllegalArgumentException(
        "Unsupported data type '" + object.getClass().getName() + "'");
  }
}
