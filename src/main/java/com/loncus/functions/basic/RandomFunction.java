package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.parser.Token;
import java.security.SecureRandom;

/** Random function produces a random value between 0 and 1. */
public class RandomFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    SecureRandom secureRandom = new SecureRandom();

    return expression.convertDoubleValue(secureRandom.nextDouble());
  }
}
