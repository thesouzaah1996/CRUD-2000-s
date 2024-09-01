package com.learn2code.Startup.service;

import com.learn2code.Startup.model.Contacts;
import com.learn2code.Startup.repository.ContactsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contacts> findAllContacts() {
        return contactsRepository.findAll();
    }

    @Transactional
    public Contacts createContact(String name, String email, String phone, String title) {

        Contacts newContact = new Contacts();
        newContact.setName(name);
        newContact.setEmail(email);
        newContact.setPhone(phone);
        newContact.setTitle(title);
        newContact.setCreated(LocalDateTime.now());

        return contactsRepository.save(newContact);
    }
}
