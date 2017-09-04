package com.bianjp.blog.entity_helper;

import org.joda.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
  @Override
  public Date convertToDatabaseColumn(LocalDate attribute) {
    return attribute.toDate();
  }

  @Override
  public LocalDate convertToEntityAttribute(Date dbData) {
    return LocalDate.fromDateFields(dbData);
  }
}
