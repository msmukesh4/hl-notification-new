package io.github.msmukesh4.email;

import io.github.msmukesh4.message.EmailMessage;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class EmailSender {

    private final String username;
    private final String password;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(EmailMessage email) throws MessagingException, IOException {
        // Gmail SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Use 465 if you prefer SSL
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Authenticator to provide username and password
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create the email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCC()));
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getBCC()));
        message.setSubject(email.getSubject());

        // Email body
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        if (email.isHtml()) {
            messageBodyPart.setContent(email.getMessage(), "text/html");
        } else {
            messageBodyPart.setText(email.getMessage());
        }

        // Attach files if any
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (email.getAttachments() != null) {
            for (File attachment : email.getAttachments()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(attachment);
                multipart.addBodyPart(attachmentPart);
            }
        }

        // Set the content of the email
        message.setContent(multipart);

        // Send the email
        Transport.send(message);
    }
}