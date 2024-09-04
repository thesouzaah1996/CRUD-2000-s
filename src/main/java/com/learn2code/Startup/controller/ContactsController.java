package com.learn2code.Startup.controller;

import com.learn2code.Startup.entity.Contacts;
import com.learn2code.Startup.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/search")
    public String searchContact(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "title", required = false) String title,
                                Model model) {
        if (id != null) {
            Optional<Contacts> contact = contactsService.findById(id);
            contact.ifPresent(c -> model.addAttribute("contacts", List.of(c)));
        } else if (name != null && !name.isEmpty()){
            model.addAttribute("contacts", contactsService.findByName(name));
        } else if (title != null && !title.isEmpty()) {
            model.addAttribute("contacts", contactsService.findByTitle(title));
        } else {
            model.addAttribute("error", "Please provide either an ID or a name to search");
        }

        return "contacts/search";
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

    @PostMapping("contacts/update/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contacts contacts) {
        contactsService.updateContact(id, contacts);
        return "redirect:/contacts";
    }
}
