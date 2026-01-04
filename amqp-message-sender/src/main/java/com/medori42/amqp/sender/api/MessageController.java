package com.medori42.amqp.sender.api;
import com.medori42.amqp.sender.business.MessageSenderService;
import com.medori42.amqp.sender.entity.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageSenderService messageSenderService;
    public MessageController(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }
    @PostMapping("/produce")
    public ResponseEntity<UserMessage> produceMessage(@RequestBody UserMessage userMessage) {
        logger.info("Received request to produce message for user: {}", userMessage.getUserName());
        UserMessage sentMessage = messageSenderService.sendMessage(userMessage);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("AMQP Message Sender Service is running");
    }
}