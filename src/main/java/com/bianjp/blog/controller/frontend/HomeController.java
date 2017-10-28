package com.bianjp.blog.controller.frontend;

import com.bianjp.blog.controller_helper.NotFoundException;
import com.bianjp.blog.entity.Post;
import com.bianjp.blog.entity.Tag;
import com.bianjp.blog.helper.TagCloud;
import com.bianjp.blog.service.PostService;
import com.bianjp.blog.service.TagService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

  private final PostService postService;
  private final TagService tagService;

  @Autowired
  public HomeController(PostService postService, TagService tagService) {
    this.postService = postService;
    this.tagService = tagService;
  }

  @GetMapping("")
  public String home(Model model) {
    List<Post> posts = postService.findLatestPublishedPosts(10);
    model.addAttribute("posts", posts);

    List<Tag> tags = tagService.findAllWithPosts();
    model.addAttribute("tagCloud", new TagCloud(tags));

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
    if (post == null) throw new NotFoundException();
    model.addAttribute("post", post);
    return "frontend/post";
  }

  @GetMapping("/posts/{id}")
  public String postPage(@PathVariable("id") int id) {
    Post post = postService.findPublishedPost(id);
    if (post == null) throw new NotFoundException();
    return "redirect:/posts/" + post.getPrettyUrl();
  }

  @GetMapping("/about")
  public String about() {
    return "frontend/about";
  }
}
