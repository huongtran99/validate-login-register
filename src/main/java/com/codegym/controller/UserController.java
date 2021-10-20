package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ModelAndView showFormRegister() {
        ModelAndView modelAndView = new ModelAndView("/user/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView showListUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            return new ModelAndView("/user/register");
        }
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/register");
        modelAndView.addObject("message", "successfully");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(User user) {
        User user1 = userService.checkLogin(user.getUserName(), user.getPassword());
        if (user1 != null) {
            return new ModelAndView("/user/index");
        }
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("message", "tai khoan or mat khau khong dung");
        return modelAndView;
    }

}
