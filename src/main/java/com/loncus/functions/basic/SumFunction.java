package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Returns the sum value of all parameters. */
@FunctionParameter(name = "value", isVarArg = true)
public class SumFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    BigDecimal sum = BigDecimal.ZERO;
    for (EvaluationValue parameter : parameterValues) {
      sum = sum.add(parameter.getNumberValue(), expression.getConfiguration().getMathContext());
    }
    return expression.convertValue(sum);
  }
}
