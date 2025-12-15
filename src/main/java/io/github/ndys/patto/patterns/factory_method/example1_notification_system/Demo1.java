package io.github.ndys.patto.patterns.factory_method.example1_notification_system;

import io.github.ndys.patto.ui.TerminalPrinter;
public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Factory Method > Notification System");

        NotificationFactory[] factories = {
            new EmailNotificationFactory(),
            new SmsNotificationFactory(),
            new PushNotificationFactory()
        };

        for (NotificationFactory factory : factories) {
            factory.sendNotification("Hello from Factory Method!");
        }
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}

