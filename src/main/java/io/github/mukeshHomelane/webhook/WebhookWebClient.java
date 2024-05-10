package io.github.mukeshHomelane.webhook;

import io.github.mukeshHomelane.message.Message;
import reactor.core.publisher.Mono;


public interface WebhookWebClient {

    public Mono<String> sendMessageToGoogleChat(Message message, String messageIdentification);

    public Mono<String> refreshCache(String key);


}
