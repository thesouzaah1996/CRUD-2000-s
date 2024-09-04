package com.learn2code.Startup.controller;

import com.learn2code.Startup.service.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewAccountController {

    @Autowired
    private NewAccountService newAccountService;

    @RequestMapping("/create")
    public String createAccount() {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String confirmPassword,
                                             Model model) {
        String result = newAccountService.createUser(username, password, confirmPassword);

        if (result.equals("New Account created successfully.")) {
            model.addAttribute("successMessage", result);
            System.out.println("New account created");
            return "login";
        } else {
            model.addAttribute("errorMessage", result);
            System.out.println("Error. New account was not created");
            return "create";
        }
    }
}
