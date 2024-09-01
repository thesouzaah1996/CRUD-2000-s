package com.learn2code.Startup.controller;

import com.learn2code.Startup.model.Contacts;
import com.learn2code.Startup.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/contacts")
    public String createContact(@RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone,
                                @RequestParam("title") String title,
                                Model model) {
        try {
            contactsService.createContact(name, email, phone, title);
            model.addAttribute("successMessage", "Contact created successfully!");
            return "redirect:/contacts";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "contacts";
        }
    }

    @PostMapping("/contacts/delete")
    public String deleteContact(@RequestParam("id") Long id, Model model) {
        try {
            contactsService.deleteContact(id);
            model.addAttribute("successMessage", "Contact deleted successfully!");
            return "redirect:/contacts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting contact: " + e.getMessage());
            return "contacts";
        }
    }
}
