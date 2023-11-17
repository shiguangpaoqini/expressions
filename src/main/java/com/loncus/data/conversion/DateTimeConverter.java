package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/** Converter to convert to the DATE_TIME data type. */
public class DateTimeConverter implements ConverterIfc {

  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {

    Instant instant;

    if (object instanceof Instant) {
      instant = (Instant) object;
    } else if (object instanceof ZonedDateTime) {
      instant = ((ZonedDateTime) object).toInstant();
    } else if (object instanceof OffsetDateTime) {
      instant = ((OffsetDateTime) object).toInstant();
    } else if (object instanceof LocalDate) {
      instant = ((LocalDate) object).atStartOfDay().atZone(configuration.getZoneId()).toInstant();
    } else if (object instanceof LocalDateTime) {
      instant = ((LocalDateTime) object).atZone(configuration.getZoneId()).toInstant();
    } else if (object instanceof Date) {
      instant = ((Date) object).toInstant();
    } else if (object instanceof Calendar) {
      instant = ((Calendar) object).toInstant();
    } else {
      throw illegalArgument(object);
    }
    return EvaluationValue.dateTimeValue(instant);
  }

  @Override
  public boolean canConvert(Object object) {
    return (object instanceof Instant
        || object instanceof ZonedDateTime
        || object instanceof OffsetDateTime
        || object instanceof LocalDate
        || object instanceof LocalDateTime
        || object instanceof Date
        || object instanceof Calendar);
  }
}
