package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;

/**
 * Converter interface to be implemented by configurable evaluation value converters. Converts an
 * arbitrary object to an {@link EvaluationValue}, using the specified configuration.
 */
public interface EvaluationValueConverterIfc {

  /**
   * Called whenever an object has to be converted to an {@link EvaluationValue}.
   *
   * @param object The object holding the value.
   * @param configuration The configuration to use.
   * @return The converted {@link EvaluationValue}.
   * @throws IllegalArgumentException if the object can't be converted.
   */
  EvaluationValue convertObject(Object object, ExpressionConfiguration configuration);
}
