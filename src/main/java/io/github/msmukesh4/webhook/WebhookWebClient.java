package io.github.msmukesh4.webhook;

import io.github.msmukesh4.message.Message;
import reactor.core.publisher.Mono;


public interface WebhookWebClient {

    public Mono<String> sendMessageToGoogleChat(Message message, String messageIdentification);

    public Mono<String> refreshCache(String key);


}
