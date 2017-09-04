package com.bianjp.blog.entity_helper;

import com.bianjp.blog.entity.Post;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PostStatusConverter implements AttributeConverter<Post.Status, Integer> {
  @Override
  public Integer convertToDatabaseColumn(Post.Status attribute) {
    return attribute.getCode();
  }

  @Override
  public Post.Status convertToEntityAttribute(Integer dbData) {
    return Post.Status.findByCode(dbData);
  }
}
