package com.bianjp.blog.controller.admin;

import com.bianjp.blog.entity.User;
import com.bianjp.blog.session.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackageClasses = DashboardController.class)
public class AdminControllerAdvice {

  @ModelAttribute
  public void setCurrentUser(Model model) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetailsImpl) {
      User user = ((UserDetailsImpl) principal).getUser();
      model.addAttribute("currentUser", user);
    } else {
      throw new RuntimeException("No current user found");
    }
  }
}
