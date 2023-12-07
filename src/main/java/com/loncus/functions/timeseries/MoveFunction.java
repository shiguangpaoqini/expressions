package com.loncus.functions.timeseries;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.data.type.TimePeriod;
import com.loncus.data.type.TimeSeriesPoint;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;

/** Returns a TimeSeriesPoint, the value of the previous n periods. */
@FunctionParameter(name = "TimeSeriesPoint")
@FunctionParameter(name = "period")
@FunctionParameter(name = "times")
public class MoveFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    TimeSeriesPoint timeSeriesPoint = parameterValues[0].getTimeSeriesPoint();
    String period = parameterValues[1].getStringValue();
    Number times = parameterValues[2].getNumberValue();
    TimePeriod timePeriod = TimePeriod.fromAlias(period);

    return expression.convertValue(
        timeSeriesPoint.setTime(timeSeriesPoint.getTime().minus(times.longValue(), timePeriod)));
  }
}
