package com.loncus.functions.datetime;

import com.loncus.functions.FunctionParameter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@FunctionParameter(name = "value", isVarArg = true)
public class DateTimeParseFunction extends AbstractDateTimeParseFunction {

  protected Instant parse(String value, String format, ZoneId zoneId) {
    Optional<Instant> instant = parseInstant(value);
    if (!instant.isPresent()) {
      instant = parseLocalDateTime(value, format, zoneId);
    }
    if (!instant.isPresent()) {
      instant = parseDate(value, format);
    }
    return instant.orElseThrow(() -> new IllegalArgumentException("Unable to parse date/time: " + value));
  }

  private Optional<Instant> parseLocalDateTime(String value, String format, ZoneId zoneId) {
    try {
      DateTimeFormatter formatter =
          (format == null
              ? DateTimeFormatter.ISO_LOCAL_DATE_TIME
              : DateTimeFormatter.ofPattern(format));
      return Optional.of(LocalDateTime.parse(value, formatter).atZone(zoneId).toInstant());
    } catch (DateTimeParseException ex) {
      return Optional.empty();
    }
  }

  private Optional<Instant> parseDate(String value, String format) {
    try {
      DateTimeFormatter formatter =
          (format == null ? DateTimeFormatter.ISO_LOCAL_DATE : DateTimeFormatter.ofPattern(format));
      LocalDate localDate = LocalDate.parse(value, formatter);
      return Optional.of(localDate.atStartOfDay().atOffset(ZoneOffset.UTC).toInstant());
    } catch (DateTimeParseException ex) {
      return Optional.empty();
    }
  }

  private Optional<Instant> parseInstant(String value) {
    try {
      return Optional.of(Instant.parse(value));
    } catch (DateTimeParseException ex) {
      return Optional.empty();
    }
  }
}
