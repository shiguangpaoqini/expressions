package com.loncus.functions.trigonometric;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns the trigonometric sine of an angle (in degrees). */
@FunctionParameter(name = "value")
public class SinFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    return expression.convertDoubleValue(
        Math.sin(Math.toRadians(parameterValues[0].getNumberValue().doubleValue())));
  }
}
