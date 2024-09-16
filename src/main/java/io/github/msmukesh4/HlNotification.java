package io.github.msmukesh4;


import io.github.msmukesh4.email.EmailSender;
import io.github.msmukesh4.message.EmailMessage;
import io.github.msmukesh4.message.Message;
import io.github.msmukesh4.webhook.MessageIdentification;
import io.github.msmukesh4.webhook.WebhookWebClient;
import io.github.msmukesh4.webhook.WebhookWebClientImpl;
import jakarta.mail.MessagingException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

public class HlNotification {

    public static Config config;

    private final WebhookWebClient webhookWebClient;

    private final Scheduler scheduler;

    private final EmailSender emailSender;


    public HlNotification(Config config){
        config.webhookUrl = config.webhookUrl + "&messageReplyOption=REPLY_MESSAGE_FALLBACK_TO_NEW_THREAD";
        HlNotification.config = config;
        webhookWebClient = new WebhookWebClientImpl();
        scheduler = Schedulers.newSingle("Notif-Thread");
        emailSender = new EmailSender(config.mailUsername, config.mailAppPassword);
    }

    /**
     *
     * This method sends the message to webhook channel.
     * @param messageIdentification represents as a channel to which message to be sent
     * @param message
     * @return
     */
    public Mono<String> sendMessageToGoogleChat(Message message, String messageIdentification){
        return webhookWebClient.sendMessageToGoogleChat(message, messageIdentification);
    }

    public Mono<String> sendMessageToGoogleChat(Message message){
        return sendMessageToGoogleChat(message, MessageIdentification.OTHERS.name());
    }

    public void sendMessageToGoogleChatAsync(Message message, String messageIdentification){
        Mono.just(message)
                .flatMap(msg -> sendMessageToGoogleChat(msg, messageIdentification))
                .subscribeOn(scheduler)
                .subscribe();
    }

    public void sendMessageToGoogleChatAsync(Message message){
        Mono.just(message)
                .flatMap(msg -> sendMessageToGoogleChat(msg, MessageIdentification.OTHERS.name()))
                .subscribeOn(scheduler)
                .subscribe();
    }

    /**
     * When we are replying to a particular thread and we want to stop it and initiate a new message
     * so we need to first clear the message thread.
     * This clears the generic Channel{@link MessageIdentification#OTHERS} for the message
     * @return
     */
    public Mono<String> refreshCache(){
        return refreshCache(MessageIdentification.OTHERS.name());
    }

    public void refreshCacheAsync(){
        refreshCache(MessageIdentification.OTHERS.name())
                .subscribeOn(scheduler)
                .subscribe();
    }

    /**
     * When we are replying to a particular thread and we want to stop it and initiate a new message
     * so we need to first clear the message thread.
     * @param key
     * @return
     */
    public Mono<String> refreshCache(String key){
        return webhookWebClient.refreshCache(key);
    }

    public void sendEmail(EmailMessage emailMessage){
        try {
            emailSender.sendEmail(emailMessage);
            System.out.println("Email sent successfully using Gmail SMTP!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}