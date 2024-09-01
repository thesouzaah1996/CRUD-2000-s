package com.learn2code.Startup.service;

import com.learn2code.Startup.model.Contacts;
import com.learn2code.Startup.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contacts> findAllContacts() {
        return contactsRepository.findAll();
    }
}
