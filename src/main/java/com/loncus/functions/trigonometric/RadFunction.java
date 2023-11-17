package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/**
 * Converts an angle measured in degrees to an approximately equivalent angle measured in radians.
 */
@FunctionParameter(name = "degrees")
public class RadFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    double deg = Math.toRadians(parameterValues[0].getNumberValue().doubleValue());

    return expression.convertDoubleValue(deg);
  }
}
