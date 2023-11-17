package com.loncus.operators.booleans;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_COMPARISON;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;

/** Less or equals of two values. */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_COMPARISON)
public class InfixLessEqualsOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands) {
    return expression.convertValue(operands[0].compareTo(operands[1]) <= 0);
  }
}
