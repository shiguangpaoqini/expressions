package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.RoundingMode;

/** Rounds the given value an integer using the rounding mode {@link RoundingMode#CEILING} */
@FunctionParameter(name = "value")
public class CeilingFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    EvaluationValue value = parameterValues[0];

    return expression.convertValue(value.getNumberValue().setScale(0, RoundingMode.CEILING));
  }
}
