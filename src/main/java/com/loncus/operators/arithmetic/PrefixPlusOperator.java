package com.loncus.operators.arithmetic;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.PrefixOperator;
import com.loncus.parser.Token;

/** Unary prefix plus. */
@PrefixOperator(leftAssociative = false)
public class PrefixPlusOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands)
      throws EvaluationException {
    EvaluationValue operator = operands[0];

    if (operator.isNumberValue()) {
      return expression.convertValue(
          operator.getNumberValue().plus(expression.getConfiguration().getMathContext()));
    } else {
      throw EvaluationException.ofUnsupportedDataTypeInOperation(operatorToken);
    }
  }
}
