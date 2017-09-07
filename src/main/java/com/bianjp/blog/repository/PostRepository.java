package com.bianjp.blog.repository;

import com.bianjp.blog.entity.Post;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  List<Post> findAllByStatus(Post.Status status, Pageable pageable);

  int countAllByStatus(Post.Status status);

  List<Post> findAllByStatusIn(Collection<Post.Status> statuses, Pageable pageable);

  int countAllByStatusIn(Collection<Post.Status> statuses);

  Post findByPublishDateAndSlugAndStatus(LocalDate publishDate, String slug, Post.Status status);
}
