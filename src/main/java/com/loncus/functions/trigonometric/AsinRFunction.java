package com.loncus.functions.trigonometric;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Returns the arc-sine (in radians). */
@FunctionParameter(name = "value")
public class AsinRFunction extends AbstractFunction {

  private static final BigDecimal MINUS_ONE = valueOf(-1);

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues)
      throws EvaluationException {

    BigDecimal parameterValue = parameterValues[0].getNumberValue();

    if (parameterValue.compareTo(ONE) > 0) {
      throw new EvaluationException(
          functionToken, "Illegal asinr(x) for x > 1: x = " + parameterValue);
    }
    if (parameterValue.compareTo(MINUS_ONE) < 0) {
      throw new EvaluationException(
          functionToken, "Illegal asinr(x) for x < -1: x = " + parameterValue);
    }
    return expression.convertDoubleValue(
        Math.asin(parameterValues[0].getNumberValue().doubleValue()));
  }
}
