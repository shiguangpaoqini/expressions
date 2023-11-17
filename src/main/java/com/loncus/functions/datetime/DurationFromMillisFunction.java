package com.loncus.functions.datetime;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;
import java.time.Duration;

@FunctionParameter(name = "value")
public class DurationFromMillisFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    BigDecimal millis = parameterValues[0].getNumberValue();
    return expression.convertValue(Duration.ofMillis(millis.longValue()));
  }
}
