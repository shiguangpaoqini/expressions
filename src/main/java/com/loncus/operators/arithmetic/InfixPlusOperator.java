package com.loncus.operators.arithmetic;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_ADDITIVE;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;
import java.time.Duration;

/**
 * Addition of numbers and strings. If one operand is a string, a string concatenation is performed.
 */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_ADDITIVE)
public class InfixPlusOperator extends AbstractOperator {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token operatorToken, EvaluationValue... operands) {
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
              .add(rightOperand.getNumberValue(), expression.getConfiguration().getMathContext()));
    } else if (leftOperand.isDateTimeValue() && rightOperand.isDurationValue()) {
      return expression.convertValue(
          leftOperand.getDateTimeValue().plus(rightOperand.getDurationValue()));
    } else if (leftOperand.isDurationValue() && rightOperand.isDurationValue()) {
      return expression.convertValue(
          leftOperand.getDurationValue().plus(rightOperand.getDurationValue()));
    } else if (leftOperand.isDateTimeValue() && rightOperand.isNumberValue()) {
      return expression.convertValue(
          leftOperand
              .getDateTimeValue()
              .plus(Duration.ofMillis(rightOperand.getNumberValue().longValue())));
    } else {
      return expression.convertValue(leftOperand.getStringValue() + rightOperand.getStringValue());
    }
  }
}
