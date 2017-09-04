package com.bianjp.blog.entity_helper;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class DateTimeConverter implements AttributeConverter<DateTime, Date> {
  @Override
  public Date convertToDatabaseColumn(DateTime attribute) {
    return attribute == null ? null : attribute.toDate();
  }

  @Override
  public DateTime convertToEntityAttribute(Date dbData) {
    return dbData == null ? null : new DateTime(dbData);
  }
}
