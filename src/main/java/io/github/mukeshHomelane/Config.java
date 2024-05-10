package io.github.mukeshHomelane;

import io.github.mukeshHomelane.message.MessageChannel;
import io.github.mukeshHomelane.util.CommonUtils;

import java.util.List;


public class Config {

    public String webhookUrl = null;
    public String applicationName = null;
    public String applicationId = null;
    public List<MessageChannel> channels = null;

    public Config(String webhookUrl, String applicationName, String applicationId, List<MessageChannel> channels) {
        this.webhookUrl = webhookUrl;
        this.applicationName = applicationName;
        this.applicationId = applicationId;
        this.channels = channels;
    }

    public Config(String webhookUrl, String applicationName, String applicationId) {
        this.webhookUrl = webhookUrl;
        this.applicationName = applicationName;
        this.applicationId = applicationId;
        this.channels = List.of(MessageChannel.WEBHOOK);
    }

    public Config(String webhookUrl, String applicationName) {
        this.webhookUrl = webhookUrl;
        this.applicationName = applicationName;
        this.applicationId = CommonUtils.generateUniqueIdentifier();
        this.channels = List.of(MessageChannel.WEBHOOK);
    }
}
