package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/**
 * Converts an angle measured in radians to an approximately equivalent angle measured in degrees.
 */
@FunctionParameter(name = "radians")
public class DegFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    double rad = Math.toDegrees(parameterValues[0].getNumberValue().doubleValue());

    return expression.convertDoubleValue(rad);
  }
}
