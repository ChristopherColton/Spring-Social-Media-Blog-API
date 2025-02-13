package com.example.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Account;
import com.example.entity.Message;

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

    //ACCOUNT CONTROL
    //register new account
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account)
    {
        if(account.getUsername().isBlank() || account.getPassword().length() < 4)
        {
            return ResponseEntity.status(400).build();
        }
        if(accountService.usernameExists(account.getUsername()))
        {
            return ResponseEntity.status(409).build();
        }

        Account newAccount = accountService.createAccount(account);

        return ResponseEntity.status(200).body(newAccount);
    }

    //login user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account)
    {
        Optional<Account> authOptional = accountService.auth(account.getUsername(), account.getPassword());
        
        if(authOptional.isPresent())
        {
            return ResponseEntity.status(200).body(authOptional.get());
        }
        else
        {
            return ResponseEntity.status(401).build();
        }
    }

    //MESSAGE CONTROL
    //create a message
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message)
    {
        if(message.getMessageText().isBlank() || message.getMessageText().length() > 255 || !accountService.existsById(message.getPostedBy()))
        {
            return ResponseEntity.status(400).build();
        }

        Message newMessage = messageService.createMessage(message);

        return ResponseEntity.status(200).body(newMessage);
    }

    //delete messages
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId)
    {
        boolean deleteMessage = messageService.deleteMessage(messageId);

        if(deleteMessage)
        {
            return ResponseEntity.status(200).body(1);
        }
        else
        {
            return ResponseEntity.status(200).body("");
        }
    }

    //update messages
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Message message)
    {
        if(message.getMessageText().isBlank() || message.getMessageText().length() > 255)
        {
            return ResponseEntity.status(400).body("");
        }

        boolean newMessage = messageService.updateMessage(messageId, message.getMessageText());

        if(newMessage)
        {
            return ResponseEntity.status(200).body(1);
        }
        else
        {
            return ResponseEntity.status(400).build();
        }
    }

    //get all messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages()
    {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    //get message by id
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer messageId)
    {
        Optional<Message> message = messageService.getMessageById(messageId);

        if(message.isPresent())
        {
            return ResponseEntity.status(200).body(message.get());
        }
        else
        {
            return ResponseEntity.status(200).body("");
        }
    }
    
    
}
