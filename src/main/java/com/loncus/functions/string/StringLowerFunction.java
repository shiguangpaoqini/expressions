package com.loncus.functions.string;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Converts the given value to lower case. */
@FunctionParameter(name = "value")
public class StringLowerFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    return expression.convertValue(parameterValues[0].getStringValue().toLowerCase());
  }
}
