package com.medori42.amqp.sender.business;
import com.medori42.amqp.sender.entity.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class MessageSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name:user.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key:user.routingkey}")
    private String routingKey;
    public MessageSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public UserMessage sendMessage(UserMessage userMessage) {
        userMessage.setMessageId(UUID.randomUUID().toString());
        userMessage.setTimestamp(LocalDateTime.now());
        logger.info("Sending message to exchange '{}' with routing key '{}': {}",
                exchangeName, routingKey, userMessage);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, userMessage);
        logger.info("Message sent successfully with ID: {}", userMessage.getMessageId());
        return userMessage;
    }
}