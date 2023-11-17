package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import java.util.ArrayList;
import java.util.List;

/** Converter to convert to the ARRAY data type. */
public class ArrayConverter implements ConverterIfc {
  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    List<EvaluationValue> array = new ArrayList<>();
    ((List) object).forEach(element -> array.add(new EvaluationValue(element, configuration)));

    return EvaluationValue.arrayValue(array);
  }

  @Override
  public boolean canConvert(Object object) {
    return object instanceof List;
  }
}
