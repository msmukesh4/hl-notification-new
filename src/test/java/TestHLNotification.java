import io.github.msmukesh4.Config;
import io.github.msmukesh4.HlNotification;
import io.github.msmukesh4.message.Message;
import io.github.msmukesh4.webhook.MessageIdentification;

public class TestHLNotification {
    public static void main(String[] args) {
        System.out.println("Test");

        HlNotification hlNotification = new HlNotification(
                new Config("https://chat.googleapis.com/v1/spaces...",
                        "SC-APP")
        );

        /*hlNotification.sendMessageToGoogleChat(new Message("First Message"))
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.sendMessageToGoogleChat(new Message("Second Message")))
                .doOnNext(System.out::println)
                .flatMap(res1 -> {
                    *//*try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*//*
                    return hlNotification.sendMessageToGoogleChat(new Message("Third Message"));
                })
                .doOnNext(System.out::println)
                .flatMap(res1 -> hlNotification.refreshCache())
                .doOnNext(System.out::println)
                .block();*/

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
    }

}
