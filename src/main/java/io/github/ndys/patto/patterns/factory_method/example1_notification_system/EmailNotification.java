package io.github.ndys.patto.patterns.factory_method.example1_notification_system;

public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }

    
}

