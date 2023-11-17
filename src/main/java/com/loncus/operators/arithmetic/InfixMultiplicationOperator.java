package com.loncus.operators.arithmetic;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_MULTIPLICATIVE;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;

/** Multiplication of two numbers. */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_MULTIPLICATIVE)
public class InfixMultiplicationOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands)
      throws EvaluationException {
    EvaluationValue leftOperand = operands[0];
    EvaluationValue rightOperand = operands[1];

    Boolean leftIsNumberOrTimeSeriesPoint =
        leftOperand.isNumberValue() || leftOperand.isTimeSeriesPoint();
    Boolean rightIsNumberOrTimeSeriesPoint =
        rightOperand.isNumberValue() || rightOperand.isTimeSeriesPoint();

    if (leftIsNumberOrTimeSeriesPoint && rightIsNumberOrTimeSeriesPoint) {
      return expression.convertValue(
          leftOperand
              .getNumberValue()
              .multiply(
                  rightOperand.getNumberValue(), expression.getConfiguration().getMathContext()));
    } else {
      throw EvaluationException.ofUnsupportedDataTypeInOperation(operatorToken);
    }
  }
}
