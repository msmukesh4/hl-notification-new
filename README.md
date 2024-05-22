# HL-NOTIFICATION

### Intro
This is a Library this is helps you integrate with google webhook library.
You can sue this library to send high priority information to a hangout group chat directly.

You can download the jar from https://hl-nucleus-dev.s3.ap-south-1.amazonaws.com/HL-Notification-v1.2.jar and add it in you local resources  folder of your application. 

```
  in your build.gradle file add this
dependencies {
     ...
     implementation files('./src/main/resources/HL-Notification-v1.jar');
}
```

### create a webhook url in google chat
<UL>
 <LI> Go to the hangout in gmail and create a space </LI>
 <LI>Go to Apps & integrations for the space</LI>
 <LI>Click on Add Webhooks</LI>
 <LI>Name the Chat app name and click Save</LI>
 <LI>Copy the Webhook link and use it in the next steps</LI>
</UL>

<br />

## Steps to crate a use the Notification Library

### 1. Initialize the notification class by your config
```
     HlNotification hlNotification = new HlNotification(
                new Config("[Webhook Link] https://chat.googleapis.com/v1/spaces/...",
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


## Live Example
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



