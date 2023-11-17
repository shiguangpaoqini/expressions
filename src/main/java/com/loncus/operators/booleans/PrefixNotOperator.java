package com.loncus.operators.booleans;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.PrefixOperator;
import com.loncus.parser.Token;

/** Boolean negation of value. */
@PrefixOperator
public class PrefixNotOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands) {
    return expression.convertValue(!operands[0].getBooleanValue());
  }
}
