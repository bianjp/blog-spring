package com.bianjp.blog.controller.admin;

import com.bianjp.blog.dto.JSONReplyDTO;
import com.bianjp.blog.entity.User;
import com.bianjp.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class ProfileController {

  private UserService userService;

  @Autowired
  public ProfileController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile")
  public String profile() {
    return "admin/profile/profile";
  }

  @GetMapping("/change-password")
  public String changePasswordPage() {
    return "admin/profile/change-password";
  }

  @PostMapping("/change-password")
  @ResponseBody
  public JSONReplyDTO changePassword(
      HttpSession session,
      @ModelAttribute(name = "currentUser", binding = false) User currentUser,
      @RequestParam String oldPassword,
      @RequestParam String password,
      @RequestParam String passwordConfirmation) {
    if (password == null || password.isEmpty()) {
      return JSONReplyDTO.fail("New password cannot be empty");
    } else if (!password.equals(passwordConfirmation)) {
      return JSONReplyDTO.fail("Password confirmation does not match new password");
    } else if (password.length() < 8) {
      return JSONReplyDTO.fail("New password must not be less than 8 characters");
    } else if (!userService.validatePassword(currentUser, oldPassword)) {
      return JSONReplyDTO.fail("Incorrect password");
    }

    userService.updatePassword(currentUser, password);

    // Must invalidate session (include remember-me token) to apply new password
    session.invalidate();
    SecurityContextHolder.getContext().setAuthentication(null);

    HashMap<String, String> data = new HashMap<>();
    data.put("url", "/login");
    return new JSONReplyDTO(
        0, "Password changed successfully. You need to relogin to apply the new password", data);
  }
}
