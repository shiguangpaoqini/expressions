package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the angle of atan2 (in degrees). */
@FunctionParameter(name = "y")
@FunctionParameter(name = "x")
public class Atan2Function extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    return expression.convertDoubleValue(
        Math.toDegrees(
            Math.atan2(
                parameterValues[0].getNumberValue().doubleValue(),
                parameterValues[1].getNumberValue().doubleValue())));
  }
}
