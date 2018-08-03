package com.bianjp.blog.controller.frontend;

import com.bianjp.blog.entity.Post;
import com.bianjp.blog.entity.Tag;
import com.bianjp.blog.repository.TagRepository;
import com.bianjp.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagController {
  private final TagRepository tagRepository;
  private final PostService postService;

  public TagController(TagRepository tagRepository, PostService postService) {
    this.tagRepository = tagRepository;
    this.postService = postService;
  }

  @GetMapping("/{name}")
  public String tagPage(Model model, @PathVariable("name") String name, Pageable pageable) {
    Tag tag = tagRepository.findByName(name);
    model.addAttribute("tag", tag);
    model.addAttribute("tagName", name);

    if (tag != null) {
      pageable =
          PageRequest.of(
              pageable.getPageNumber(),
              pageable.getPageSize(),
              new Sort(Sort.Direction.DESC, "publishDate"));
      Page<Post> postsPage = postService.findPublishedPostsByTag(tag, pageable);
      model.addAttribute("page", postsPage);
    }

    return "frontend/tags/tag";
  }
}
