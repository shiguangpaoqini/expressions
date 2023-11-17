package com.loncus.operators.arithmetic;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.PrefixOperator;
import com.loncus.parser.Token;

/** Unary prefix minus. */
@PrefixOperator(leftAssociative = false)
public class PrefixMinusOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands)
      throws EvaluationException {
    EvaluationValue operand = operands[0];

    if (operand.isNumberValue()) {
      return expression.convertValue(
          operand.getNumberValue().negate(expression.getConfiguration().getMathContext()));
    } else {
      throw EvaluationException.ofUnsupportedDataTypeInOperation(operatorToken);
    }
  }
}
