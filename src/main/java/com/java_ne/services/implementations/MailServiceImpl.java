package com.java_ne.services.implementations;

import com.java_ne.exceptions.BadRequestException;
import com.java_ne.exceptions.InternalServerErrorException;
import com.java_ne.services.interfaces.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    @Value("${app_name}")
    private String appName;

    @Override
    @Async
    public void sendEmail(String to, String subject, String content, boolean isHtmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(to);
            helper.setFrom(appName);
            helper.setSubject(subject);
            helper.setText(content, isHtmlContent);
            mailSender.send(message);
        } catch (MessagingException exception) {
            throw new BadRequestException("Failed To Send Email: " + exception.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException("An error occurred while sending email");
        }
    }

    @Override
    public void sendTransactionEmail(String to, String subject, String firstName, String lastName, String transactionType, String amount, String account) throws MessagingException {
        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("firstName", firstName);
        ctx.setVariable("lastName", lastName);
        ctx.setVariable("transactionType", transactionType);
        ctx.setVariable("amount", amount);
        ctx.setVariable("account", account);

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject(subject);
        message.setFrom(appName);
        message.setTo(to);


        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("transaction-email", ctx);
        message.setText(htmlContent, true); // true = isHtml

        // Send email
        this.mailSender.send(mimeMessage);
    }
}