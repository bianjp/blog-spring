package com.bianjp.blog.controller.admin;

import com.bianjp.blog.dto.JSONReplyDTO;
import com.bianjp.blog.entity.Post;
import com.bianjp.blog.exception.InvalidTagException;
import com.bianjp.blog.form.PostForm;
import com.bianjp.blog.helper.PaginationHelper;
import com.bianjp.blog.service.PostService;
import com.bianjp.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/posts")
public class PostsController {

  private final PostService postService;
  private final TagService tagService;

  @Autowired
  public PostsController(PostService postService, TagService tagService) {
    this.postService = postService;
    this.tagService = tagService;
  }

  /**
   * Handle invalid tag exception when saving tags
   */
  @ExceptionHandler(InvalidTagException.class)
  @ResponseBody
  public JSONReplyDTO handleInvalidTagException(InvalidTagException exception) {
    return JSONReplyDTO.fail(exception.getMessage());
  }

  // Page: Posts list, or drafts list
  @GetMapping("")
  public String index(
      HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
      @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    PageRequest pageRequest = new PageRequest(page - 1, pageSize, sort);

    List<Post> posts = postService.findNormalPosts(pageRequest);
    int totalCount = postService.countNormalPosts();

    model.addAttribute("posts", posts);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", Math.ceil(totalCount * 1.0 / pageSize));
    PaginationHelper.setPageLink(model, request);
    return "admin/posts/index";
  }

  @GetMapping("/drafts")
  public String drafts(
      HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
      @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    PageRequest pageRequest = new PageRequest(page - 1, pageSize, sort);

    List<Post> posts = postService.findDrafts(pageRequest);
    int totalCount = postService.countDrafts();

    model.addAttribute("posts", posts);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", Math.ceil(totalCount * 1.0 / pageSize));
    PaginationHelper.setPageLink(model, request);
    return "admin/posts/drafts";
  }

  // Page: Add new post
  @GetMapping("/new")
  public String newPage(Model model) {
    model.addAttribute("post", new Post());
    model.addAttribute("tags", tagService.findAll(new Sort("name")));
    return "admin/posts/new";
  }

  // Page: Edit post
  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") Post post) {
    model.addAttribute("post", post);
    model.addAttribute("tags", tagService.findAll(new Sort("name")));
    return "admin/posts/edit";
  }

  // API: Add new post
  @PostMapping("")
  @ResponseBody
  public JSONReplyDTO create(@Valid @RequestBody PostForm postForm, Errors errors) {
    if (errors.hasFieldErrors()) {
      FieldError error = errors.getFieldError();
      return JSONReplyDTO.fail(error.getField() + ": " + error.getDefaultMessage());
    }

    postService.create(postForm);

    return JSONReplyDTO.success("Created successfully");
  }

  // API: Update post
  @PutMapping("/{id}")
  @ResponseBody
  @Transactional
  public JSONReplyDTO update(
      @PathVariable("id") Post post, @Valid @RequestBody PostForm postForm, Errors errors) {
    if (errors.hasFieldErrors()) {
      FieldError error = errors.getFieldError();
      return JSONReplyDTO.fail(error.getField() + ": " + error.getDefaultMessage());
    }

    postService.update(post, postForm);

    return JSONReplyDTO.success("Updated successfully");
  }

  // API: Delete post
  @DeleteMapping("/{id}")
  @ResponseBody
  public JSONReplyDTO destroy(@PathVariable("id") Post post) {
    if (post.getStatus() == Post.Status.DELETED) {
      return JSONReplyDTO.fail("Record already deleted");
    }

    postService.logicalDelete(post);

    return new JSONReplyDTO();
  }
}
