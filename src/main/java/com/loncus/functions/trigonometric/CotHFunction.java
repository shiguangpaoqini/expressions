package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the hyperbolic co-tangent of a value. */
@FunctionParameter(name = "value", nonZero = true)
public class CotHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: coth(x) = 1 / tanh(x) */
    return expression.convertDoubleValue(
        1 / Math.tanh(parameterValues[0].getNumberValue().doubleValue()));
  }
}
