package com.travellers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2LoginController {
    @GetMapping("/loginSuccess")
    public String loginSuccess(OAuth2User principal) {
// Process user information and customize your logic
        return "redirect:/";
    }}