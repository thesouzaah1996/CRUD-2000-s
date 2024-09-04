package com.learn2code.Startup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateAccountController {

    @RequestMapping("/createAccount")
    public String createAccount() {
        return "createAccount";
    }
}
