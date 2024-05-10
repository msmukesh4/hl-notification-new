package io.github.mukeshHomelane;


import io.github.mukeshHomelane.message.Message;
import io.github.mukeshHomelane.webhook.MessageIdentification;
import io.github.mukeshHomelane.webhook.WebhookWebClient;
import io.github.mukeshHomelane.webhook.WebhookWebClientImpl;
import reactor.core.publisher.Mono;

public class HlNotification {

    public static Config config;

    private final WebhookWebClient webhookWebClient;

    public HlNotification(Config config){
        config.webhookUrl = config.webhookUrl + "&messageReplyOption=REPLY_MESSAGE_FALLBACK_TO_NEW_THREAD";
        HlNotification.config = config;
        webhookWebClient = new WebhookWebClientImpl();
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

    /**
     * When we are replying to a particular thread and we want to stop it and initiate a new message
     * so we need to first clear the message thread.
     * This clears the generic Channel{@link MessageIdentification#OTHERS} for the message
     * @return
     */
    public Mono<String> refreshCache(){
        return refreshCache(MessageIdentification.OTHERS.name());
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
}