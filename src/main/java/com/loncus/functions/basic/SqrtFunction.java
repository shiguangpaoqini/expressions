package com.loncus.functions.basic;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/** Square root function, uses the standard {@link BigDecimal#sqrt(MathContext)} implementation. */
@FunctionParameter(name = "value", nonNegative = true)
public class SqrtFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {

    /*
     * From The Java Programmers Guide To numerical Computing
     * (Ronald Mak, 2003)
     */
    BigDecimal x = parameterValues[0].getNumberValue();
    MathContext mathContext = expression.getConfiguration().getMathContext();

    if (x.compareTo(BigDecimal.ZERO) == 0) {
      return expression.convertValue(BigDecimal.ZERO);
    }
    BigInteger n = x.movePointRight(mathContext.getPrecision() << 1).toBigInteger();

    int bits = (n.bitLength() + 1) >> 1;
    BigInteger ix = n.shiftRight(bits);
    BigInteger ixPrev;
    BigInteger test;
    do {
      ixPrev = ix;
      ix = ix.add(n.divide(ix)).shiftRight(1);
      // Give other threads a chance to work
      Thread.yield();
      test = ix.subtract(ixPrev).abs();
    } while (test.compareTo(BigInteger.ZERO) != 0 && test.compareTo(BigInteger.ONE) != 0);

    return expression.convertValue(new BigDecimal(ix, mathContext.getPrecision()));
  }
}
