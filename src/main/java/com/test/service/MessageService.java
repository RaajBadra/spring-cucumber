package com.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.domain.MessageItem;
import com.test.repository.MessageRepository;

@Service
public class MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private MessageRepository messageRepository;

    @Transactional
    public void createMessageItem(MessageItem messageItem) {
        messageRepository.save(messageItem);
    }

    public MessageItem getMessages(String correlationId) {
      return messageRepository.findMessageByCorrelationId(correlationId);
    }


}
