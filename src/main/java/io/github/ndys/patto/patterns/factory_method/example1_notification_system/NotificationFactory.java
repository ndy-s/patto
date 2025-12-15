package io.github.ndys.patto.patterns.factory_method.example1_notification_system;

public abstract class NotificationFactory {

    protected abstract Notification createNotification();

    public void sendNotification(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
    
}

