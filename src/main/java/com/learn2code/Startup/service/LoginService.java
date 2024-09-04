package com.learn2code.Startup.service;

import com.learn2code.Startup.entity.Contacts;
import com.learn2code.Startup.entity.Login;
import com.learn2code.Startup.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Optional<Login> validateContact(String username, String password) {
        Optional<Login> userOPT = loginRepository.findByUsername(username);

        if (userOPT.isPresent()) {
            Login contact = userOPT.get();
            if (contact.getPassword().equals(password)) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }
}
