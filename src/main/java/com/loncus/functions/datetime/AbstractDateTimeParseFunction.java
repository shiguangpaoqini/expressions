package com.loncus.functions.datetime;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.functions.AbstractFunction;
import com.loncus.parser.Token;
import java.time.Instant;
import java.time.ZoneId;

public abstract class AbstractDateTimeParseFunction extends AbstractFunction {
  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    ZoneId zoneId = expression.getConfiguration().getZoneId();
    Instant instant;

    if (parameterValues.length < 2) {
      instant = parse(parameterValues[0].getStringValue(), null, zoneId);
    } else {
      instant =
          parse(parameterValues[0].getStringValue(), parameterValues[1].getStringValue(), zoneId);
    }
    return expression.convertValue(instant);
  }

  protected abstract Instant parse(String value, String format, ZoneId zoneId);
}
