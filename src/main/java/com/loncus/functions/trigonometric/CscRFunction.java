package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the co-secant (in radians). */
@FunctionParameter(name = "value", nonZero = true)
public class CscRFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: csc(x) = 1 / sin(x) */
    return expression.convertDoubleValue(
        1 / Math.sin(parameterValues[0].getNumberValue().doubleValue()));
  }
}
