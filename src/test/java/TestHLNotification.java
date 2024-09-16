import io.github.msmukesh4.Config;
import io.github.msmukesh4.HlNotification;
import io.github.msmukesh4.message.EmailMessage;
import io.github.msmukesh4.message.Message;
import io.github.msmukesh4.message.MessageChannel;
import io.github.msmukesh4.webhook.MessageIdentification;

import java.util.ArrayList;
import java.util.List;

public class TestHLNotification {
    public static void main(String[] args) {
        System.out.println("Test");

//        HlNotification hlNotification = new HlNotification(
//                new Config("https://chat.googleapis.com/v1/spaces...",
//                        "SC-APP")
//        );

        /*hlNotification.sendMessageToGoogleChat(new Message("First Message"))
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.sendMessageToGoogleChat(new Message("Second Message")))
                .doOnNext(System.out::println)
                .flatMap(res1 -> {
                    *//*try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*//*
                    return hlNotification.sendMessageToGoogleChat(new Message("Third Message"));
                })
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.refreshCache())
                .doOnNext(System.out::println)
                .block();*/

        /*hlNotification.sendMessageToGoogleChat(new Message("SC-First Message"), MessageIdentification.SC_PRICE_NOTIFICATION.name())
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.sendMessageToGoogleChat(new Message("First Message")))
                .doOnNext(System.out::println)
                .flatMap(res1 -> {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return hlNotification.sendMessageToGoogleChat(new Message("SC-Second Message"), MessageIdentification.SC_PRICE_NOTIFICATION.name());
                })
                .doOnNext(System.out::println)
                .flatMap(res1 -> {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return hlNotification.sendMessageToGoogleChat(new Message("Second Message"));
                })
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.refreshCache())
                .flatMap(it -> hlNotification.refreshCache(MessageIdentification.SC_PRICE_NOTIFICATION.name()))
                .doOnNext(System.out::println)
                .block();*/

//        hlNotification.sendMessageToGoogleChatAsync(new Message("Test"));


        HlNotification notification = new HlNotification(
                new Config("https://chat.googleapis.com/v1/spaces...",
                        "SC-APP","SC-APP",
                        List.of(MessageChannel.EMAIL),
                        "buildanything.infra@gmail.com",
                        "...")
        );

//        EmailMessage message = new EmailMessage("Message");
//        message.setFrom("buildanything.infra@gmail.com");
//        message.setTo("mukesh.s@homelane.com");
//        message.setCC("msmukesh4@gmail.com");
//        message.setBCC("");
//        message.setHtml(false);
//        message.setSubject("Test Subject");
//        message.setMessage("Test Message Body");
//        message.setId("Test-Id-123");

        String userName = "Mike";
        String userEmail = "mukesh.s@homelane.com";
        String userPassword = "Pass123";

//        // Compose the HTML email body
//        String htmlBody = "<html>"
//                + "<body style='font-family: Arial, sans-serif;'>"
//                + "<h2 style='color: #2E86C1;'>Welcome to Our Platform, " + userName + "!</h2>"
//                + "<p>Dear " + userName + ",</p>"
//                + "<p>Thank you for registering with us. Below are your login credentials:</p>"
//                + "<ul>"
//                + "<li><strong>Email:</strong> " + userEmail + "</li>"
//                + "<li><strong>Password:</strong> " + userPassword + "</li>"
//                + "</ul>"
//                + "<p>For security reasons, we recommend you change your password after logging in.</p>"
//                + "<p>To get started, click the link below to login:</p>"
//                + "<a href='https://www.example.com/login' style='color: white; background-color: #28a745; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Login to Your Account</a>"
//                + "<p>If you have any questions, feel free to <a href='https://www.example.com/contact'>contact us</a> anytime.</p>"
//                + "<p>Best regards,<br>The Team</p>"
//                + "<hr>"
//                + "<p style='font-size: 12px; color: gray;'>This is an automated message, please do not reply. If you did not register, please ignore this email.</p>"
//                + "</body>"
//                + "</html>";

        // Compose the HTML email body with enhanced design
        String htmlBody = "<html>"
                + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
                + "<div style='max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'>"
                + "<div style='text-align: center;'>"
                + "<img src='https://ddnb1aopd6qzc.cloudfront.net/HomelaneLogo.png' alt='Company Logo' style='max-width: 200px; margin-bottom: 8px;' />"
                + "</div>"
                + "<h2 style='color: #2E86C1; text-align: center;'>Welcome to Our Platform, " + userName + "!</h2>"
                + "<p style='font-size: 16px; color: #333;'>Dear " + userName + ",</p>"
                + "<p style='font-size: 16px; color: #555;'>Thank you for registering with us! We are thrilled to have you join our platform. Below are your login details:</p>"
                + "<div style='background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin-bottom: 20px;'>"
                + "<p style='font-size: 16px; color: #333;'><strong>Email:</strong> " + userEmail + "</p>"
                + "<p style='font-size: 16px; color: #333;'><strong>Password:</strong> " + userPassword + "</p>"
                + "<p style='font-size: 14px; color: #999;'>For security reasons, please change your password after logging in.</p>"
                + "</div>"
                + "<div style='text-align: center; margin-bottom: 20px;'>"
                + "<a href='https://www.example.com/login' style='font-size: 18px; text-decoration: none; color: white; background-color: #28a745; padding: 12px 24px; border-radius: 5px;'>Login to Your Account</a>"
                + "</div>"
                + "<p style='font-size: 16px; color: #555;'>If you have any questions, feel free to <a href='https://www.example.com/contact' style='color: #2E86C1;'>contact us</a> anytime.</p>"
                + "<p style='font-size: 16px; color: #555;'>Best regards,<br><strong>The Team</strong></p>"
                + "<hr style='border: 0; border-top: 1px solid #ddd; margin: 40px 0;' />"
                + "<p style='font-size: 12px; color: #999; text-align: center;'>This is an automated message, please do not reply. If you did not register, please ignore this email.</p>"
                + "</div>"
                + "</body>"
                + "</html>";

        EmailMessage message = new EmailMessage("Message");
        message.setFrom("buildanything.infra@gmail.com");
        message.setTo("mukesh.s@homelane.com");
        message.setCC("msmukesh4@gmail.com");
        message.setBCC("");
        message.setHtml(true);
        message.setSubject("Test Subject");
        message.setMessage(htmlBody);
        message.setId("Test-Id-123");


        notification.sendEmail(message);


    }

}
