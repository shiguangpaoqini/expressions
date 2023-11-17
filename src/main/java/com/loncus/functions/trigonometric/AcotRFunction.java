package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the arc-co-tangent (in radians). */
@FunctionParameter(name = "value", nonZero = true)
public class AcotRFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /* Formula: acot(x) = (pi / 2) - atan(x) */
    return expression.convertDoubleValue(
        (Math.PI / 2) - Math.atan(parameterValues[0].getNumberValue().doubleValue()));
  }
}
