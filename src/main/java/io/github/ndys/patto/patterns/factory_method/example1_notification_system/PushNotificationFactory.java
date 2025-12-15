package io.github.ndys.patto.patterns.factory_method.example1_notification_system;

public class PushNotificationFactory extends NotificationFactory {

    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}

