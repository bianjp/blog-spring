package com.bianjp.blog.repository;

import com.bianjp.blog.entity.Post;
import com.bianjp.blog.entity.Tag;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  Page<Post> findAllByStatus(Post.Status status, Pageable pageable);

  Page<Post> findAllByStatusIn(Collection<Post.Status> statuses, Pageable pageable);

  Post findByPublishDateAndSlugAndStatus(LocalDate publishDate, String slug, Post.Status status);

  Post findByIdAndStatus(int id, Post.Status status);

  Page<Post> findAllByTagsContainsAndStatus(Tag tag, Post.Status status, Pageable pageable);
}
