package com.loncus.functions.datetime;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.time.LocalDateTime;
import java.time.ZoneId;

@FunctionParameter(name = "values", isVarArg = true, nonNegative = true)
public class DateTimeFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    int year = parameterValues[0].getNumberValue().intValue();
    int month = parameterValues[1].getNumberValue().intValue();
    int day = parameterValues[2].getNumberValue().intValue();
    int hour = parameterValues.length >= 4 ? parameterValues[3].getNumberValue().intValue() : 0;
    int minute = parameterValues.length >= 5 ? parameterValues[4].getNumberValue().intValue() : 0;
    int second = parameterValues.length >= 6 ? parameterValues[5].getNumberValue().intValue() : 0;
    int nanoOfs = parameterValues.length >= 7 ? parameterValues[6].getNumberValue().intValue() : 0;

    ZoneId zoneId = expression.getConfiguration().getZoneId();
    return expression.convertValue(
        LocalDateTime.of(year, month, day, hour, minute, second, nanoOfs)
            .atZone(zoneId)
            .toInstant());
  }
}
