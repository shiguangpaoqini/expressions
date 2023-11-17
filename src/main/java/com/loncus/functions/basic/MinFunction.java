package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Returns the minimum value of all parameters. */
@FunctionParameter(name = "value", isVarArg = true)
public class MinFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    BigDecimal min = null;
    for (EvaluationValue parameter : parameterValues) {
      if (min == null || parameter.getNumberValue().compareTo(min) < 0) {
        min = parameter.getNumberValue();
      }
    }
    return expression.convertValue(min);
  }
}
