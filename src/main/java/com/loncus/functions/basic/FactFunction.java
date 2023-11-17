package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Factorial function, calculates the factorial of a base value. */
@FunctionParameter(name = "base")
public class FactFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    int number = parameterValues[0].getNumberValue().intValue();
    BigDecimal factorial = BigDecimal.ONE;
    for (int i = 1; i <= number; i++) {
      factorial =
          factorial.multiply(
              new BigDecimal(i, expression.getConfiguration().getMathContext()),
              expression.getConfiguration().getMathContext());
    }
    return expression.convertValue(factorial);
  }
}
