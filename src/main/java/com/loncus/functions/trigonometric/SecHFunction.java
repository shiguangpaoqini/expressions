package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the hyperbolic secant. */
@FunctionParameter(name = "value", nonZero = true)
public class SecHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: sech(x) = 1 / cosh(x) */
    return expression.convertDoubleValue(
        1 / Math.cosh(parameterValues[0].getNumberValue().doubleValue()));
  }
}
