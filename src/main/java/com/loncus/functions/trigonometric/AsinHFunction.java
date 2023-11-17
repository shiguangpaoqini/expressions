package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the hyperbolic arc-sine. */
@FunctionParameter(name = "value")
public class AsinHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: asinh(x) = ln(x + sqrt(x^2 + 1)) */
    double value = parameterValues[0].getNumberValue().doubleValue();
    return expression.convertDoubleValue(Math.log(value + (Math.sqrt(Math.pow(value, 2) + 1))));
  }
}
