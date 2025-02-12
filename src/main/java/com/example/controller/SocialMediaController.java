package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Account;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
@RequestMapping
public class SocialMediaController 
{
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    //new account
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account)
    {
        if(account.getUsername().isBlank() || account.getPassword().length() < 4)
        {
            return ResponseEntity.status(400).build();
        }
        return null;
    }
}
