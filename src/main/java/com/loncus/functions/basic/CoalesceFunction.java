package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/**
 * Returns the first non-null parameter, or {@link EvaluationValue#nullValue()} if all parameters
 * are null.
 */
@FunctionParameter(name = "value", isVarArg = true)
public class CoalesceFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    for (EvaluationValue parameter : parameterValues) {
      if (!parameter.isNullValue()) {
        return parameter;
      }
    }
    return EvaluationValue.nullValue();
  }
}
