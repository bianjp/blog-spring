package com.bianjp.blog.entity_helper;

import org.joda.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
  @Override
  public Date convertToDatabaseColumn(LocalDate attribute) {
    return attribute == null ? null : attribute.toDate();
  }

  @Override
  public LocalDate convertToEntityAttribute(Date dbData) {
    return dbData == null ? null : LocalDate.fromDateFields(dbData);
  }
}
