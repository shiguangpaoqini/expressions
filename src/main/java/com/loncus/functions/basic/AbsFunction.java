package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Absolute (non-negative) value. */
@FunctionParameter(name = "value")
public class AbsFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    return expression.convertValue(
        parameterValues[0].getNumberValue().abs(expression.getConfiguration().getMathContext()));
  }
}
