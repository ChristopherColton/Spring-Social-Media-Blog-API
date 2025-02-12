package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;

@Service
public class AccountService 
{
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) //create new user account
    {
        return accountRepository.save(account);
    }

    public boolean usernameExists(String username) //check if username exists
    {
        return accountRepository.findByUsername(username).isPresent();
    }

    public boolean existsById(Integer id) //check if user id exists
    {
        return accountRepository.existsById(id);
    }

    public Optional<Account> auth(String username, String password) //authenticate user login info
    {
        return accountRepository.findByUsernameAndPassword(username, password);
    }
}
