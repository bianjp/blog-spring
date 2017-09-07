package com.bianjp.blog.controller.frontend;

import com.bianjp.blog.entity.Post;
import com.bianjp.blog.service.PostService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

  private PostService postService;

  @Autowired
  public HomeController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("")
  public String home(Model model) {
    List<Post> posts = postService.findLatestPublishedPosts(10);
    model.addAttribute("posts", posts);
    return "frontend/home";
  }

  @GetMapping("/posts/{year}/{month}/{day}/{slug}")
  public String post(
      Model model,
      @PathVariable("year") int year,
      @PathVariable("month") int month,
      @PathVariable("day") int day,
      @PathVariable("slug") String slug) {
    Post post = postService.findPublishedPost(new LocalDate(year, month, day), slug);
    model.addAttribute("post", post);
    return "frontend/post";
  }
}
