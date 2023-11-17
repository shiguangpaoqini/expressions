package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import java.math.BigDecimal;

/** Converter to convert to the NUMBER data type. */
public class NumberConverter implements ConverterIfc {

  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    BigDecimal bigDecimal;

    if (object instanceof BigDecimal) {
      bigDecimal = (BigDecimal) object;
    } else if (object instanceof Double) {
      bigDecimal = new BigDecimal(Double.toString((double) object), configuration.getMathContext());
    } else if (object instanceof Float) {
      bigDecimal = BigDecimal.valueOf((float) object);
    } else if (object instanceof Integer) {
      bigDecimal = BigDecimal.valueOf((int) object);
    } else if (object instanceof Long) {
      bigDecimal = BigDecimal.valueOf((long) object);
    } else if (object instanceof Short) {
      bigDecimal = BigDecimal.valueOf((short) object);
    } else if (object instanceof Byte) {
      bigDecimal = BigDecimal.valueOf((byte) object);
    } else {
      throw illegalArgument(object);
    }

    return EvaluationValue.numberValue(bigDecimal);
  }

  @Override
  public boolean canConvert(Object object) {
    return (object instanceof BigDecimal
        || object instanceof Double
        || object instanceof Float
        || object instanceof Integer
        || object instanceof Long
        || object instanceof Short
        || object instanceof Byte);
  }
}
