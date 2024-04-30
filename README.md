# HL-NOTIFICATION



## Steps to crate a use the Notification Library

### 1. Initialize the notification class by your config
```
     HlNotification hlNotification = new HlNotification(
                new Config("https://chat.googleapis.com/v1/spaces/...",
                        "SC-APP")
     );
```
### 2. Call the notification channel and send message
channel are basically threads of the chat, if you want to create a ladder of reply 
on top of the base message then you should keep on sending the message
```
        // All the messages are going to the default channel
        hlNotification.sendMessageToGoogleChat(new Message("First Message"))
                        
       // Send message to a custom channel 
       hlNotification.sendMessageToGoogleChat(new Message("SC-First Message"), MessageIdentification.SC_PRICE_NOTIFICATION.name());
```

### 3. After sending all the message close the channel
After sending all the messages you need to close the threads on which 
the message was sent so that next time you can start a new 
thread to send messages 
```
    // Close default thread
    hlNotification.refreshCache()
    
    // Close custom channel
    hlNotification.refreshCache(MessageIdentification.SC_PRICE_NOTIFICATION.name()))
    
```
<br /> <br /> <br />


## Example
```
        HlNotification hlNotification = new HlNotification(
                new Config("https://chat.googleapis.com/v1/spaces...",
                        "SC-APP")
        );

        hlNotification.sendMessageToGoogleChat(new Message("SC-First Message"), MessageIdentification.SC_PRICE_NOTIFICATION.name())
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
                .block();
```



