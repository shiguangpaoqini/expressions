package com.loncus.functions.datetime;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.time.Duration;

@FunctionParameter(name = "value")
public class DurationParseFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    String text = parameterValues[0].getStringValue();
    return expression.convertValue(Duration.parse(text));
  }
}
