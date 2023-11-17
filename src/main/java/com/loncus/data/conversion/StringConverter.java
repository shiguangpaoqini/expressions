package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;

/** Converter to convert to the STRING data type. */
public class StringConverter implements ConverterIfc {

  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    String string;

    if (object instanceof CharSequence) {
      string = ((CharSequence) object).toString();
    } else if (object instanceof Character) {
      string = ((Character) object).toString();
    } else {
      throw illegalArgument(object);
    }

    return EvaluationValue.stringValue(string);
  }

  @Override
  public boolean canConvert(Object object) {
    return (object instanceof CharSequence || object instanceof Character);
  }
}
