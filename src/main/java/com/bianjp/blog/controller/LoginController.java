package com.bianjp.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @GetMapping("login")
  public String loginPage(Model model, @RequestParam(required = false) String error) {
    model.addAttribute("hasError", error != null);
    return "login";
  }
}
