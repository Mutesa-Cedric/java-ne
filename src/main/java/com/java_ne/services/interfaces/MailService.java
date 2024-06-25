package com.java_ne.services.interfaces;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmail(String to, String subject, String content, boolean isHtmlContent);
    void sendTransactionEmail(String to, String subject, String firstName, String lastName,String transactionType, String amount, String account) throws MessagingException;

}