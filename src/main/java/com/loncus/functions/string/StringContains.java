package com.loncus.functions.string;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns true, if the string contains the substring (case-insensitive). */
@FunctionParameter(name = "string")
@FunctionParameter(name = "substring")
public class StringContains extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    String string = parameterValues[0].getStringValue();
    String substring = parameterValues[1].getStringValue();
    return expression.convertValue(string.toUpperCase().contains(substring.toUpperCase()));
  }
}
