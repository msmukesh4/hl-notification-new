package io.github.mukeshHomelane.webhook;

import com.google.gson.Gson;
import io.github.mukeshHomelane.HlNotification;
import io.github.mukeshHomelane.message.Message;
import io.github.mukeshHomelane.util.CommonUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WebhookWebClientImpl implements WebhookWebClient {

    private final HttpClient wWebClient;

    private final Map<String, String> messageIdentificationCache = new HashMap<>();


    public WebhookWebClientImpl () {
         this.wWebClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(3, ChronoUnit.MINUTES))
                .build();
    }

    @Override
    public Mono<String> sendMessageToGoogleChat(Message message, String messageIdentification) {
        String gsonMessage = CommonUtils.convertStringToGsonString(message.getMessage(), messageIdentificationCache.get(getMessageIdentificationCache(messageIdentification)));
        try {
            String s = wWebClient.sendAsync(
                  HttpRequest.newBuilder()
                          .uri(URI.create(HlNotification.config.webhookUrl))
                          .header("Content-Type", "application/json")
                          .POST(HttpRequest.BodyPublishers.ofString(gsonMessage))
                          .build(),  HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .get();
            return Mono.just(new Gson().toJson(s));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    private void publishCacheMessage(String messageIdentification) {
        if(messageIdentificationCache.get(messageIdentification) == null){
            messageIdentificationCache.put(messageIdentification, CommonUtils.generateUniqueIdentifier());
        }
    }

    public String getMessageIdentificationCache(String messageIdentification){
        if(!messageIdentificationCache.containsKey(messageIdentification) || messageIdentificationCache.get(messageIdentification) == null) {
            publishCacheMessage(messageIdentification);
        }
        return messageIdentification;
    }

    @Override
    public Mono<String> refreshCache(String key){
        this.messageIdentificationCache.put(key, null);
        return Mono.just("Cache Refreshed Successfully");
    }
}
