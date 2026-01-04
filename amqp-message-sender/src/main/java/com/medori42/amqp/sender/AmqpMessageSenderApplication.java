package com.medori42.amqp.sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class AmqpMessageSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpMessageSenderApplication.class, args);
    }
}