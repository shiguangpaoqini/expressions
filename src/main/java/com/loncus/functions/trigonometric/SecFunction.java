package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the secant (in degrees). */
@FunctionParameter(name = "value", nonZero = true)
public class SecFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: sec(x) = 1 / cos(x) */
    return expression.convertDoubleValue(
        1 / Math.cos(Math.toRadians(parameterValues[0].getNumberValue().doubleValue())));
  }
}
