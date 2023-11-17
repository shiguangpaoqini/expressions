package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the co-tangent of an angle (in degrees). */
@FunctionParameter(name = "value", nonZero = true)
public class CotFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: cot(x) = cos(x) / sin(x) = 1 / tan(x) */
    return expression.convertDoubleValue(
        1 / Math.tan(Math.toRadians(parameterValues[0].getNumberValue().doubleValue())));
  }
}
