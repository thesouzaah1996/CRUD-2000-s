package com.learn2code.Startup.service;

import com.learn2code.Startup.entity.Contacts;
import com.learn2code.Startup.repository.ContactsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contacts> findAllContacts() {
        return contactsRepository.findAll(Sort.by(Sort.Direction.DESC, "created"));
    }

    public Optional<Contacts> findById(Long id) {
        return contactsRepository.findById(id);
    }

    public List<Contacts> findByName(String name) {
        return contactsRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Contacts> findByTitle(String title) {
        return contactsRepository.findByTitle(title);
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

    @Transactional
    public void deleteContact(Long id) {
        if (contactsRepository.existsById(id)) {
            contactsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Contact not found with ID: " + id);
        }
    }

    @Transactional
    public Contacts updateContact(Long id, Contacts updateContact) {
        return contactsRepository.findById(id)
                .map(contacts -> {
                    contacts.setName(updateContact.getName());
                    contacts.setEmail(updateContact.getEmail());
                    contacts.setPhone(updateContact.getPhone());
                    contacts.setTitle(updateContact.getTitle());
                    return contactsRepository.save(contacts);
                })
                .orElseThrow(() -> new RuntimeErrorException(new Error("Contact with id " + id + " was not found.")));
    }


}
