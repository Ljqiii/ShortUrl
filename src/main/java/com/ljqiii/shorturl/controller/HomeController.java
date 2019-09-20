package com.ljqiii.shorturl.controller;

import com.ljqiii.shorturl.model.NormalUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String indexpage(UsernamePasswordAuthenticationToken token) {
        if (token == null) return "urladd";
        NormalUser normalUser = (NormalUser) token.getPrincipal();

        if (normalUser != null && normalUser.hasRole("ROLE_admin")) {
            return "redirect:/admin";
        } else if (normalUser != null && normalUser.hasRole("ROLE_user")) {
            return "urladd";
        }

        return "urladd";
    }





}
