package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.UserDto;
import com.sda.onlinestore.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultController {
    @Autowired
    private UserDetailService userService;

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") UserDto userDto) {
        userService.register(userDto);
        return "/user";
    }
    @GetMapping("/")
    public String home1() {
        return "/index";
    }
    @GetMapping("/home")
    public String home() {
        return "/index";
    }
    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }
    @GetMapping("/admin/addUserPage")
    public String addUserPage() {
        return "/addUserPage";
    }
    @GetMapping("/admin/addDeleteUsers")
    public String addDeleteUsers() {
        return "/addDeleteUsers";
    }
    @GetMapping("/user")
    public String user() {
        return "/user";
    }
    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
