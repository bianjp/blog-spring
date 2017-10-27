package com.bianjp.blog.service;

import com.bianjp.blog.entity.Tag;
import com.bianjp.blog.exception.InvalidTagException;
import com.bianjp.blog.repository.TagRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<Tag> findAll(Sort sort) {
    return tagRepository.findAll(sort);
  }

  public Tag findOrCreateByName(String name) {
    Tag tag = tagRepository.findByName(name);
    if (tag == null) {
      try{
        tag = new Tag();
        tag.setName(name);
        tag.setPostCount(0);
        tagRepository.save(tag);
      } catch (ConstraintViolationException exception) {
        ConstraintViolation<?> violation = exception.getConstraintViolations().iterator().next();
        throw new InvalidTagException("Invalid tag " + violation.getPropertyPath() + ": " + violation.getMessage());
      }
    }
    return tag;
  }

  public void updatePostCount(int id) {
    tagRepository.updatePostCount(id);
  }
}
