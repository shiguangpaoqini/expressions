package com.loncus.operators.arithmetic;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_ADDITIVE;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;
import java.time.Duration;

/** Subtraction of two numbers. */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_ADDITIVE)
public class InfixMinusOperator extends AbstractOperator {

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
              .subtract(
                  rightOperand.getNumberValue(), expression.getConfiguration().getMathContext()));

    } else if (leftOperand.isDateTimeValue() && rightOperand.isDateTimeValue()) {
      return expression.convertValue(
          Duration.ofMillis(
              leftOperand.getDateTimeValue().toEpochMilli()
                  - rightOperand.getDateTimeValue().toEpochMilli()));

    } else if (leftOperand.isDateTimeValue() && rightOperand.isDurationValue()) {
      return expression.convertValue(
          leftOperand.getDateTimeValue().minus(rightOperand.getDurationValue()));
    } else if (leftOperand.isDurationValue() && rightOperand.isDurationValue()) {
      return expression.convertValue(
          leftOperand.getDurationValue().minus(rightOperand.getDurationValue()));
    } else if (leftOperand.isDateTimeValue() && rightOperand.isNumberValue()) {
      return expression.convertValue(
          leftOperand
              .getDateTimeValue()
              .minus(Duration.ofMillis(rightOperand.getNumberValue().longValue())));
    } else {
      throw EvaluationException.ofUnsupportedDataTypeInOperation(operatorToken);
    }
  }
}
