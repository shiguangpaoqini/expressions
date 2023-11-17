package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the arc hyperbolic cotangent. */
@FunctionParameter(name = "value")
public class AcotHFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: acoth(x) = log((x + 1) / (x - 1)) * 0.5 */
    double value = parameterValues[0].getNumberValue().doubleValue();
    return expression.convertDoubleValue(Math.log((value + 1) / (value - 1)) * 0.5);
  }
}
