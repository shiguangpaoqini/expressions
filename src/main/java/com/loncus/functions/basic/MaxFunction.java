package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Returns the maximum value of all parameters. */
@FunctionParameter(name = "value", isVarArg = true)
public class MaxFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    BigDecimal max = null;
    for (EvaluationValue parameter : parameterValues) {
      if (max == null || parameter.getNumberValue().compareTo(max) > 0) {
        max = parameter.getNumberValue();
      }
    }
    return expression.convertValue(max);
  }
}
