package com.medori42.amqp.sender.configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name:user.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.queue.name:user.queue}")
    private String queueName;
    @Value("${rabbitmq.routing.key:user.routingkey}")
    private String routingKey;
    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue userQueue() {
        return QueueBuilder.durable(queueName).build();
    }
    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userQueue).to(userExchange).with(routingKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}