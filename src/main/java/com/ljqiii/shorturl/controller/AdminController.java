package com.ljqiii.shorturl.controller;

import com.ljqiii.shorturl.model.NormalUser;
import com.ljqiii.shorturl.repository.UserRepository;
import com.ljqiii.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminhomepage(Model model) {
        ArrayList<NormalUser> adminUsers = userRepository.selectAllAdmin();
        model.addAttribute("adminUsers", adminUsers);

        return "admin";
    }

    @GetMapping("/admin/deladminuser/{userid}")
    public String deladminuser(@PathVariable("userid") int userid, Model model) {
        if (userService.deleteUser(userid) == true) {
            model.addAttribute("ok", true);
        }
        return "deleteresult";
    }


    @PostMapping("/admin/addadminuser")
    public String addadminuser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               RedirectAttributes model) {
        ArrayList<String> errmsg = new ArrayList<>();
        if (!password.equals(repeatpassword)) {
            errmsg.add("密码不一致");
            model.addAttribute("errmsg", errmsg);
            return "redirect:/admin";

        }

        if (userRepository.findByUsername(username) != null) {
            errmsg.add("用户名已存在");
            model.addAttribute("errmsg", errmsg);
            return "redirect:/admin";

        }
        userService.newUser(username, password, true);

        return "redirect:/admin";
    }
}
