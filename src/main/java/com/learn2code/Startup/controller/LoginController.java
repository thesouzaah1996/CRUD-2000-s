package com.learn2code.Startup.controller;

import com.learn2code.Startup.entity.Login;
import com.learn2code.Startup.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        Optional<Login> login = loginService.validateContact(username, password);

        if (login.isPresent()) {
            model.addAttribute("username", login.get().getUsername());
            return "redirect:/contacts";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
}
