package com.java_ne.controllers;

import com.java_ne.dtos.message.CreateUpdateMessage;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Message;
import com.java_ne.services.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/create")
    public void createMessage(@RequestBody CreateUpdateMessage message) {
        messageService.createMessage(message);
    }

    @PutMapping("/update/{messageId}")
    public ResponseEntity<ApiResponse<Message>> updateMessage(@RequestBody CreateUpdateMessage message, @PathVariable Long messageId) {
        return messageService.updateMessage(message, messageId);
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<ApiResponse<Message>> deleteMessage(@PathVariable Long messageId) {
        return messageService.deleteMessage(messageId);
    }

    @GetMapping("/id/{messageId}")
    public ResponseEntity<ApiResponse<Message>> getMessage(@PathVariable Long messageId) {
        return messageService.getMessage(messageId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<Message>>> getMessagesByCustomer(@PathVariable Long customerId) {
        return messageService.getMessagesByCustomer(customerId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Message>>> getAllMessages() {
        return messageService.getAllMessages();
    }
}