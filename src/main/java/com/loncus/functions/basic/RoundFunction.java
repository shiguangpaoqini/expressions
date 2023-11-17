package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/**
 * Rounds the given value to the specified scale, using the {@link java.math.MathContext} of the
 * expression configuration.
 */
@FunctionParameter(name = "value")
@FunctionParameter(name = "scale")
public class RoundFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    EvaluationValue value = parameterValues[0];
    EvaluationValue precision = parameterValues[1];

    return expression.convertValue(
        value
            .getNumberValue()
            .setScale(
                precision.getNumberValue().intValue(),
                expression.getConfiguration().getMathContext().getRoundingMode()));
  }
}
