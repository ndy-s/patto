package io.github.ndys.patto.patterns.factory_method.example1_notification_system;

public class SmsNotificationFactory extends NotificationFactory {

    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }

    
}

