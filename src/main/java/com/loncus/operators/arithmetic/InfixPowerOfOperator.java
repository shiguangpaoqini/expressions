package com.loncus.operators.arithmetic;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_POWER;

import com.loncus.EvaluationException;
import com.loncus.Expression;
import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import com.loncus.operators.AbstractOperator;
import com.loncus.operators.InfixOperator;
import com.loncus.parser.Token;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Power of operator, calculates the power of right operand of left operand. The precedence is read
 * from the configuration during parsing.
 *
 * @see #getPrecedence(ExpressionConfiguration)
 */
@InfixOperator(precedence = OPERATOR_PRECEDENCE_POWER, leftAssociative = false)
public class InfixPowerOfOperator extends AbstractOperator {

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
      /*-
       * Thanks to Gene Marin:
       * http://stackoverflow.com/questions/3579779/how-to-do-a-fractional-power-on-bigdecimal-in-java
       */

      MathContext mathContext = expression.getConfiguration().getMathContext();
      BigDecimal v1 = leftOperand.getNumberValue();
      BigDecimal v2 = rightOperand.getNumberValue();

      int signOf2 = v2.signum();
      double dn1 = v1.doubleValue();
      v2 = v2.multiply(new BigDecimal(signOf2)); // n2 is now positive
      BigDecimal remainderOf2 = v2.remainder(BigDecimal.ONE);
      BigDecimal n2IntPart = v2.subtract(remainderOf2);
      BigDecimal intPow = v1.pow(n2IntPart.intValueExact(), mathContext);
      BigDecimal doublePow = BigDecimal.valueOf(Math.pow(dn1, remainderOf2.doubleValue()));

      BigDecimal result = intPow.multiply(doublePow, mathContext);
      if (signOf2 == -1) {
        result = BigDecimal.ONE.divide(result, mathContext.getPrecision(), RoundingMode.HALF_UP);
      }
      return expression.convertValue(result);
    } else {
      throw EvaluationException.ofUnsupportedDataTypeInOperation(operatorToken);
    }
  }

  @Override
  public int getPrecedence(ExpressionConfiguration configuration) {
    return configuration.getPowerOfPrecedence();
  }
}
