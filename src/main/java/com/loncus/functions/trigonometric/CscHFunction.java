package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the co-secant. */
@FunctionParameter(name = "value", nonZero = true)
public class CscHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: csch(x) = 1 / sinh(x) */
    return expression.convertDoubleValue(
        1 / Math.sinh(parameterValues[0].getNumberValue().doubleValue()));
  }
}
