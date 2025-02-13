package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.Optional;
import java.util.List;

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

    public boolean updateMessage(Integer id, String messageText)
    {
        Optional<Message> messageOptional = messageRepository.findById(id);

        if(messageOptional.isPresent())
        {
            Message message = messageOptional.get();

            message.setMessageText(messageText);
            messageRepository.save(message);

            return true;
        }
        return false;
    }

    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id)
    {
        return messageRepository.findById(id);
    }

    public List<Message> getAllMessagesByUser(Integer accountId)
    {
        return messageRepository.findByPostedBy(accountId);
    }
}
