package com.loncus.functions.datetime;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@FunctionParameter(name = "value", isVarArg = true)
public class DateTimeFormatFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    String formatted;
    ZoneId zoneId = expression.getConfiguration().getZoneId();
    if (parameterValues.length < 2) {
      formatted = parameterValues[0].getDateTimeValue().atZone(zoneId).toLocalDateTime().toString();
    } else {
      DateTimeFormatter formatter =
          DateTimeFormatter.ofPattern(parameterValues[1].getStringValue());
      formatted =
          parameterValues[0].getDateTimeValue().atZone(zoneId).toLocalDateTime().format(formatter);
    }
    return expression.convertValue(formatted);
  }
}
