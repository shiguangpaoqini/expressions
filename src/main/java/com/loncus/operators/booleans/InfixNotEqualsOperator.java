package com.loncus.operators.booleans;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_EQUALITY;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;

/** No equality of two values. */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_EQUALITY)
public class InfixNotEqualsOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands) {
    return expression.convertValue(!operands[0].equals(operands[1]));
  }
}
