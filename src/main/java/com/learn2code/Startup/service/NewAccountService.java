package com.learn2code.Startup.service;

import com.learn2code.Startup.entity.Login;
import com.learn2code.Startup.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewAccountService {

    @Autowired
    private LoginRepository loginRepository;

    public String createUser(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        if (loginRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        Login newAccount = new Login();
        newAccount.setUsername(username);
        newAccount.setPassword(password);

        loginRepository.save(newAccount);

        return "New Account created successfully.";
    }
}
