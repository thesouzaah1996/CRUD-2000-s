package com.learn2code.Startup.controller;

import com.learn2code.Startup.model.Contacts;
import com.learn2code.Startup.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/contacts")
    public String listAllContacts(Model model) {
        List<Contacts> contacts = contactsService.findAllContacts();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
}
