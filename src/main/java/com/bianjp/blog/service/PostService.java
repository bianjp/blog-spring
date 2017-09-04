package com.bianjp.blog.service;

import com.bianjp.blog.entity.Post;
import com.bianjp.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {
  @Autowired private PostRepository postRepository;

  private static List<Post.Status> normalStatuses =
      Arrays.asList(Post.Status.PUBLISHED, Post.Status.UNPUBLISHED);

  public List<Post> findDrafts(Pageable pageable) {
    return postRepository.findAllByStatus(Post.Status.DRAFT, pageable);
  }

  public int countDrafts() {
    return postRepository.countAllByStatus(Post.Status.DRAFT);
  }

  public List<Post> findNormalPosts(Pageable pageable) {
    return postRepository.findAllByStatusIn(normalStatuses, pageable);
  }

  public int countNormalPosts() {
    return postRepository.countAllByStatusIn(normalStatuses);
  }
}
