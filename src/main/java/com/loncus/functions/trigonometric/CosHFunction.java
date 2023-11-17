package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the hyperbolic cosine of a value. */
@FunctionParameter(name = "value")
public class CosHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    return expression.convertDoubleValue(
        Math.cosh(parameterValues[0].getNumberValue().doubleValue()));
  }
}
