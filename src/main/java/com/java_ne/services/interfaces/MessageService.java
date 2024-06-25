package com.java_ne.services.interfaces;

import com.java_ne.dtos.message.CreateUpdateMessage;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    public void createMessage(CreateUpdateMessage message);
    public ResponseEntity<ApiResponse<Message>> updateMessage(CreateUpdateMessage message, Long messageId);
    public ResponseEntity<ApiResponse<List<Message>>> getAllMessages();
    public ResponseEntity<ApiResponse<Message>> getMessage(Long messageId);
    public ResponseEntity<ApiResponse<List<Message>>> getMessagesByCustomer(Long customerId);
    public ResponseEntity<ApiResponse<Message>> deleteMessage(Long messageId);
}
