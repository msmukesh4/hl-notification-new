package io.github.mukeshHomelane.message;

import java.util.UUID;

public class Message {

    public Message(String message){
        this.message = message;
        this.ts = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
    }

    private String message;
    private long ts;
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
