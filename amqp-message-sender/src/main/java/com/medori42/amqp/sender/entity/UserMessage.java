package com.medori42.amqp.sender.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String messageContent;
    private LocalDateTime timestamp;
    private String messageId;
}