package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.Optional;

@Service
public class MessageService 
{
    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message)
    {
        return messageRepository.save(message);
    }

    public boolean deleteMessage(Integer id)
    {
        if(messageRepository.existsById(id))
        {
            messageRepository.deleteById(id);

            return true;
        }
        return false;
    }
}
