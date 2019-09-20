package com.ljqiii.shorturl.controller;


import com.ljqiii.shorturl.model.NormalUser;
import com.ljqiii.shorturl.repository.UserRepository;
import com.ljqiii.shorturl.repository.UserRoleRepository;
import com.ljqiii.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class UserController {

    //不需要注册



//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserRoleRepository userRoleRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @GetMapping("register")
//    public String register() {
//        return "register";
//    }
//
//    @PostMapping("register")
//    public String registerpost(@RequestParam("username") String username,
//                               @RequestParam("password") String password,
//                               @RequestParam("repeatpassword") String repeatpassword,
//                               Model model) {
//        ArrayList<String> errmsg = new ArrayList<>();
//        if (!password.equals(repeatpassword)) {
//            errmsg.add("密码不一致");
//            model.addAttribute("errmsg", errmsg);
//            return "register";
//        }
//
//        if (userRepository.findByUsername(username) != null) {
//            errmsg.add("用户名已存在");
//            model.addAttribute("errmsg", errmsg);
//            return "register";
//        }
//        userService.newUser(username, password,false);
//
//        return "redirect:/login";
//    }

}
