package com.hl.notification.webhook;

import com.hl.notification.message.Message;
import reactor.core.publisher.Mono;


public interface WebhookWebClient {

    public Mono<String> sendMessageToGoogleChat(Message message, String messageIdentification);

    public Mono<String> refreshCache(String key);


}
