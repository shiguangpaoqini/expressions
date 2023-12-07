package com.loncus.functions.timeseries;

import com.loncus.Expression;
import com.loncus.data.EvaluationValue;
import com.loncus.data.type.Time;
import com.loncus.data.type.TimePeriod;
import com.loncus.data.type.TimeSeries;
import com.loncus.data.type.TimeSeriesPoint;
import com.loncus.functions.AbstractFunction;
import com.loncus.functions.FunctionParameter;
import com.loncus.parser.Token;
import java.math.BigDecimal;

/** Returns a TimeSeriesPoint, the value of the previous . */
@FunctionParameter(name = "TimeSeriesPoint")
@FunctionParameter(name = "period")
@FunctionParameter(name = "times")
public class MovingAvgFunction extends AbstractFunction {

  @Override
  public EvaluationValue evaluate(
      Expression expression, Token functionToken, EvaluationValue... parameterValues) {
    TimeSeriesPoint timeSeriesPoint = parameterValues[0].getTimeSeriesPoint();
    String period = parameterValues[1].getStringValue();
    Number times = parameterValues[2].getNumberValue();
    TimePeriod timePeriod = TimePeriod.fromAlias(period);

    Time beginTime = timeSeriesPoint.getTime().minus(times.longValue(), timePeriod);
    Time endTime = timeSeriesPoint.getTime();

    TimeSeries timeSeries = timeSeriesPoint.getTimeSeries().slice(beginTime, endTime);
    BigDecimal avg =
        timeSeries.getValues().stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(
                new BigDecimal(timeSeries.getValues().size()),
                expression.getConfiguration().getMathContext());

    return expression.convertValue(avg);
  }
}
